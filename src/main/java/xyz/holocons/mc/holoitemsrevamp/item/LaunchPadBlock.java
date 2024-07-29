package xyz.holocons.mc.holoitemsrevamp.item;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.HeightMap;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Smoker;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.Nullable;

import com.destroystokyo.paper.MaterialTags;
import com.strangeone101.holoitemsapi.item.BlockAbility;
import com.strangeone101.holoitemsapi.item.CustomItem;
import com.strangeone101.holoitemsapi.tracking.BlockLocation;
import com.strangeone101.holoitemsapi.tracking.CustomBlockStorage;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.util.SimpleExpiringSet;

public class LaunchPadBlock extends CustomItem implements BlockAbility {

    private static final String name = "launch_pad";
    private static final Material material = Material.SMOKER;
    private static final Component displayName = Component.text("Launch Pad", NamedTextColor.WHITE);
    private static final List<Component> lore = List.of(
            Component.text("Light to launch a package", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));

    private static final Pattern destinationPattern = Pattern.compile("(\\d+)\\s+(\\d+)\\s+(\\w*)");

    private final CustomBlockStorage customBlockTracker;
    private final DestinationCache destinationCache;

    public LaunchPadBlock(HoloItemsRevamp plugin) {
        super(plugin, name, material, displayName, lore);
        customBlockTracker = plugin.getTrackingManager();
        destinationCache = new DestinationCache();
        register();
    }

    @Override
    protected Recipe getRecipe() {
        final var recipe = new ShapedRecipe(getKey(), buildStack(null));
        recipe.shape("a a", "bcb", "ded");
        recipe.setIngredient('a', Material.SMOOTH_STONE_SLAB);
        recipe.setIngredient('b', Material.GLASS);
        recipe.setIngredient('c', Material.LODESTONE);
        recipe.setIngredient('d', Material.STONE);
        recipe.setIngredient('e', Material.CAMPFIRE);
        return recipe;
    }

    // TODO: The actual functionality?

    private @Nullable Block findNearestLaunchPad(final Block destination) {
        final var launchPadLocation = customBlockTracker.getNearestBlock(destination, false, 96,
                (location, ability) -> ability instanceof LaunchPadBlock);
        var block = launchPadLocation.world().getHighestBlockAt(launchPadLocation.x(), launchPadLocation.z(),
                HeightMap.WORLD_SURFACE);
        while (block.getY() > launchPadLocation.y()) {
            if (block.getType() == Material.AIR || isPackage(block)) {
                block = block.getRelative(BlockFace.DOWN);
            } else {
                break;
            }
        }
        return customBlockTracker.getAbility(block) instanceof LaunchPadBlock ? block : null;
    }

    /**
     * Parses the coordinates of the destination from given string. Failure to get
     * coordinates results in returning the package to the originating Launch Pad
     * 
     * @param destination        String containing x coordinate, z coordinate, and
     *                           optional world name
     * @param defaultDestination The originating Launch Pad
     * @return The Block where a package should be sent
     */
    private static Block parseDestinationOrDefault(final String destination, final Block defaultDestination) {
        final var matcher = destinationPattern.matcher(destination);
        if (matcher.find()) {
            try {
                final var x = Integer.parseInt(matcher.group(0));
                final var z = Integer.parseInt(matcher.group(1));
                final var worldName = matcher.group(2);
                final var world = worldName.isEmpty() ? defaultDestination.getWorld() : Bukkit.getWorld(worldName);
                return world.getBlockAt(x, 0, z);
            } catch (Exception e) {
            }
        }
        return defaultDestination;
    }

    /**
     * Check whether given Launch Pad is on cooldown
     * 
     * @param block The Launch Pad
     */
    private static boolean testCooldown(Block block) {
        return !(block instanceof Smoker smoker) || smoker.getBurnTime() > 0;
    }

    /**
     * Puts given Launch Pad on cooldown for some time.
     * 
     * @param block        The Launch Pad
     * @param cooldownTime The cooldown duration in ticks. TODO: Check that it
     *                     actually is ticks
     */
    private static void setCooldown(Block block, short cooldownTime) {
        if (block instanceof Smoker smoker) {
            smoker.setBurnTime(cooldownTime);
        }
    }

    /**
     * Launches a package. Does not touch launch-pad data (ex cooldown), only
     * manipulates the package block.
     * 
     * @param pack A package to be launched.
     */
    private static void launchPackage(Block pack) {
        throw new UnsupportedOperationException("unimplemented");
    }

    /**
     * Tries to get a package associated with this launch pad.
     * 
     * @return The package associated with this LaunchPad
     */
    private static Block getPackage(Block launchPad) {
        throw new UnsupportedOperationException("unimplemented");
    }

    private static boolean isPackage(final Block block) {
        return block.getType() == Material.BARREL || MaterialTags.SHULKER_BOXES.isTagged(block);
    }

    private static class DestinationCache {

        private final Object2ObjectOpenHashMap<BlockLocation, BlockLocation> destinationMap = new Object2ObjectOpenHashMap<>();
        private final SimpleExpiringSet<BlockLocation> expiringSet = new SimpleExpiringSet<>(600);

        public BlockLocation get(final BlockLocation origin) {
            if (expiringSet.test(origin)) {
                expiringSet.add(origin);
                return destinationMap.get(origin);
            }
            if (expiringSet.isEmpty()) {
                destinationMap.clear();
            }
            return null;
        }

        public void set(final BlockLocation origin, final BlockLocation destination) {
            expiringSet.add(origin);
            destinationMap.put(origin, destination);
        }
    }
}
