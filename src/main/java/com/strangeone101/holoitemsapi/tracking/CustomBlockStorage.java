package com.strangeone101.holoitemsapi.tracking;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;

import com.strangeone101.holoitemsapi.item.BlockAbility;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

/**
 * Shoutout to Flo0 (a.k.a 7Smiley7 on Spigot) for sharing the block tracking
 * concept which initially guided the design of our own system.
 *
 * @see <a href=
 *      "https://www.spigotmc.org/threads/tracking-blocks-that-were-placed-by-players.500216/">Resource
 *      thread</a>
 */
public class CustomBlockStorage {

    public static final String FILENAME = "blocks.json";

    private final Logger logger;
    private final File dataFolder;
    private final Object2ObjectOpenHashMap<BlockLocation, BlockAbility> trackedBlocks;
    private final LongOpenHashSet touchedChunks;

    public CustomBlockStorage(HoloItemsRevamp plugin) {
        this.logger = plugin.getLogger();
        this.dataFolder = plugin.getDataFolder();
        this.trackedBlocks = new Object2ObjectOpenHashMap<>();
        this.touchedChunks = new LongOpenHashSet();
    }

    public void loadTrackedBlocks() {
        final var file = new File(dataFolder, FILENAME);
        if (!file.exists()) {
            return;
        }

        if (!trackedBlocks.isEmpty()) {
            throw new IllegalStateException("Block tracker already has data!");
        }

        final Object2ObjectOpenHashMap<BlockLocation, BlockAbility> invalidBlocks = new Object2ObjectOpenHashMap<>();

        try (final var reader = new GsonReader(file)) {
            reader.readBlocks(trackedBlocks, invalidBlocks);
        } catch (IOException e) {
            final var invalidBlocksFolder = new File(dataFolder, "invalid-blocks");
            invalidBlocksFolder.mkdir();

            final var invalidBlocksFile = new File(invalidBlocksFolder, Instant.now().toString() + ".json");
            file.renameTo(invalidBlocksFile);
            throw new RuntimeException(e);
        }

        if (!invalidBlocks.isEmpty()) {
            logger.warning("Invalid blocks will be discarded!");

            final var invalidBlocksFolder = new File(dataFolder, "invalid-blocks");
            invalidBlocksFolder.mkdir();

            final var invalidBlocksFile = new File(invalidBlocksFolder, Instant.now().toString() + ".json");

            try (final var writer = new GsonWriter(invalidBlocksFile)) {
                writer.writeBlocks(invalidBlocks);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        trackedBlocks.keySet().stream().mapToLong(BlockLocation::chunkKey).forEach(touchedChunks::add);
    }

    public void saveTrackedBlocks() {
        dataFolder.mkdir();

        final var file = new File(dataFolder, FILENAME);

        try (final var writer = new GsonWriter(file)) {
            writer.writeBlocks(trackedBlocks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean contains(final Block block) {
        return trackedBlocks.containsKey(new BlockLocation(block));
    }

    public void set(final Block block, final BlockAbility ability) {
        final var location = new BlockLocation(block);
        trackedBlocks.put(location, ability);
        touchedChunks.add(location.chunkKey());
    }

    public BlockAbility unset(final Block block) {
        return trackedBlocks.remove(new BlockLocation(block));
    }

    public BlockAbility getAbility(final Block block) {
        return trackedBlocks.get(new BlockLocation(block));
    }

    public BlockLocation getNearestBlock(final Block block, final boolean sphere, final int radius,
            final BiPredicate<? super BlockLocation, ? super BlockAbility> predicate) {
        final var location = new BlockLocation(block);
        final var radiusSquared = radius * radius;
        final var optionalEntry = trackedBlocks.entrySet().stream()
                .filter(entry -> predicate.test(entry.getKey(), entry.getValue())
                        && distanceSquared(location, entry.getKey(), sphere) <= radiusSquared)
                .min((a, b) -> distanceSquared(location, a.getKey(), sphere)
                        - distanceSquared(location, b.getKey(), sphere));
        return optionalEntry.map(entry -> entry.getKey()).orElse(null);
    }

    private static int distanceSquared(final BlockLocation first, final BlockLocation second, final boolean sphere) {
        if (!first.worldKey().equals(second.worldKey())) {
            return Integer.MAX_VALUE;
        }
        final var x = first.x() - second.x();
        final var y = sphere ? first.y() - second.y() : 0;
        final var z = first.z() - second.z();
        return x * x + y * y + z * z;
    }

    public void forEachBlockInChunk(final UUID worldKey, final long chunkKey,
            final BiConsumer<? super BlockLocation, ? super BlockAbility> action) {
        if (!touchedChunks.contains(chunkKey)) {
            return;
        }
        trackedBlocks.forEach((location, ability) -> {
            if (location.chunkKey() == chunkKey && location.worldKey().equals(worldKey)) {
                action.accept(location, ability);
            }
        });
    }

    public void forEachLoadedBlock(final UUID worldKey,
            final BiConsumer<? super BlockLocation, ? super BlockAbility> action) {
        if (Bukkit.getWorld(worldKey) == null) {
            return;
        }
        trackedBlocks.forEach((location, ability) -> {
            if (location.worldKey().equals(worldKey) && location.isLoaded()) {
                action.accept(location, ability);
            }
        });
    }
}
