package xyz.holocons.mc.holoitemsrevamp.enchantment;

import java.util.UUID;

import org.bukkit.Axis;
import org.bukkit.Bukkit;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Orientable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import com.destroystokyo.paper.MaterialTags;
import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantmentAbility;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.integration.Integrations;

public class Splinter extends CustomEnchantment implements EnchantmentAbility {

    private final HoloItemsRevamp plugin;

    private static final Object2ObjectArrayMap<UUID, SplinterContext> contextMap = new Object2ObjectArrayMap<>();

    public Splinter(HoloItemsRevamp plugin) {
        super(plugin, "splinter");
        this.plugin = plugin;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack itemStack) {
        return MaterialTags.AXES.isTagged(itemStack);
    }

    @Override
    public @NotNull Component displayName(int i) {
        return Component.text()
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
                .append(Component.text("Splinter"))
                .build();
    }

    @Override
    public int getCostMultiplier() {
        return 12;
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, ItemStack itemStack) {
        final var block = event.getBlock();
        if (!Integrations.WORLDGUARD.canUseEnchantment(block.getLocation(), Splinter.class)) {
            return;
        }

        final var type = SplinterType.get(block);
        if (type == SplinterType.INCOMPATIBLE) {
            return;
        }

        final var playerId = event.getPlayer().getUniqueId();
        final SplinterContext context;
        if (contextMap.containsKey(playerId)) {
            context = contextMap.get(playerId);
        } else {
            context = new SplinterContext(block, 32);
            if (context.shouldSplinter(block)) {
                contextMap.put(playerId, context);
            }
        }

        if (!context.shouldSplinter(block)) {
            return;
        }

        if (--context.remainingCharges <= 0) {
            contextMap.remove(playerId);
        } else {
            context.search(block).forEach(nextBlock -> scheduleSplinterAbility(context, playerId, nextBlock));
        }
    }

    private void scheduleSplinterAbility(final SplinterContext context, final UUID playerId, final Block block) {
        new BukkitRunnable() {

            @Override
            public void run() {
                if (context != contextMap.get(playerId)) {
                    return;
                }

                --context.scheduledSplinters;

                final var player = Bukkit.getPlayer(playerId);
                if (!context.shouldSplinter(block) || !isSplinterToolInHand(player)) {
                    contextMap.remove(playerId);
                    return;
                }

                player.breakBlock(block);

                if (context.scheduledSplinters <= 0) {
                    contextMap.remove(playerId);
                }
            }
        }.runTaskLater(plugin, ++context.scheduledSplinters);
    }

    private boolean isSplinterToolInHand(final Player player) {
        return player != null && player.getInventory().getItemInMainHand().containsEnchantment(this);
    }

    private static class SplinterContext {

        private static final ObjectList<BlockFace> GENERIC_TRUNK_SEARCH_PATTERN = ObjectList.of(BlockFace.UP,
                BlockFace.EAST, BlockFace.SOUTH_WEST, BlockFace.NORTH_WEST, BlockFace.NORTH_EAST);
        private static final ObjectList<BlockFace> BROWN_SHROOM_BLOCK_SEARCH_PATTERN = ObjectList.of(BlockFace.UP,
                BlockFace.EAST, BlockFace.SOUTH_WEST, BlockFace.NORTH_WEST, BlockFace.NORTH_EAST, BlockFace.EAST,
                BlockFace.EAST, BlockFace.SOUTH, BlockFace.SOUTH, BlockFace.WEST, BlockFace.SOUTH, BlockFace.WEST,
                BlockFace.WEST, BlockFace.NORTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.NORTH, BlockFace.EAST,
                BlockFace.NORTH, BlockFace.EAST, BlockFace.EAST, BlockFace.EAST, BlockFace.EAST, BlockFace.SOUTH,
                BlockFace.SOUTH, BlockFace.SOUTH, BlockFace.SOUTH, BlockFace.WEST, BlockFace.SOUTH, BlockFace.WEST,
                BlockFace.WEST, BlockFace.WEST, BlockFace.WEST, BlockFace.NORTH, BlockFace.WEST, BlockFace.NORTH,
                BlockFace.NORTH, BlockFace.NORTH, BlockFace.NORTH, BlockFace.EAST, BlockFace.NORTH, BlockFace.EAST,
                BlockFace.EAST, BlockFace.EAST, BlockFace.EAST);
        private static final ObjectList<BlockFace> RED_SHROOM_BLOCK_TOP_SEARCH_PATTERN = ObjectList.of(BlockFace.UP,
                BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.WEST, BlockFace.NORTH, BlockFace.NORTH,
                BlockFace.EAST, BlockFace.EAST);
        private static final ObjectList<BlockFace> RED_SHROOM_BLOCK_RING_SEARCH_PATTERN = ObjectList.of(
                BlockFace.EAST_NORTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST,
                BlockFace.WEST, BlockFace.NORTH_WEST, BlockFace.NORTH, BlockFace.NORTH, BlockFace.NORTH_EAST,
                BlockFace.EAST, BlockFace.EAST);

        private BlockState originState;
        private int remainingCharges;
        private int scheduledSplinters;

        private SplinterContext(final Block origin, final int remainingCharges) {
            this.originState = origin.getState();
            this.remainingCharges = remainingCharges;
            this.scheduledSplinters = 0;
        }

        private boolean shouldSplinter(final Block block) {
            // FIXME
            return materialMatches(originState, block) && switch (SplinterType.get(block)) {
                case SHROOM_STEM -> positionMatchesXZ(originState, block) && !materialMatchesAny(originState,
                        block.getRelative(BlockFace.EAST), block.getRelative(BlockFace.SOUTH),
                        block.getRelative(BlockFace.WEST), block.getRelative(BlockFace.NORTH));
                case SHROOM_BLOCK -> true;
                case GENERIC_TRUNK -> positionMatchesXZ(originState, block);
                case GENERIC_BRANCH -> !positionMatchesXZ(originState, block);
                default -> false;
            };
        }

        private ObjectList<Block> search(final Block block) {
            // FIXME
            return switch (SplinterType.get(block)) {
                case SHROOM_STEM -> {
                    final var blockAbove = block.getRelative(BlockFace.UP);
                    yield switch (blockAbove.getType()) {
                        case MUSHROOM_STEM -> ObjectList.of(blockAbove);
                        case BROWN_MUSHROOM_BLOCK -> {
                            this.originState = blockAbove.getState();
                            this.remainingCharges = 45;
                            yield search(block, BROWN_SHROOM_BLOCK_SEARCH_PATTERN);
                        }
                        case RED_MUSHROOM_BLOCK -> {
                            this.originState = blockAbove.getState();
                            this.remainingCharges = 45;
                            final var blocks = search(block, RED_SHROOM_BLOCK_TOP_SEARCH_PATTERN);
                            var ringStartBlock = block;
                            blocks.addAll(search(ringStartBlock, RED_SHROOM_BLOCK_RING_SEARCH_PATTERN));
                            ringStartBlock = ringStartBlock.getRelative(BlockFace.DOWN);
                            blocks.addAll(search(ringStartBlock, RED_SHROOM_BLOCK_RING_SEARCH_PATTERN));
                            ringStartBlock = ringStartBlock.getRelative(BlockFace.DOWN);
                            blocks.addAll(search(ringStartBlock, RED_SHROOM_BLOCK_RING_SEARCH_PATTERN));
                            yield blocks;
                        }
                        default -> ObjectLists.emptyList();
                    };
                }
                case GENERIC_TRUNK -> search(block, GENERIC_TRUNK_SEARCH_PATTERN);
                default -> ObjectLists.emptyList();
            };
        }

        private ObjectList<Block> search(final Block block, final ObjectList<BlockFace> pattern) {
            final var blocks = new ObjectArrayList<Block>();
            var nextBlock = block;
            for (final var face : pattern) {
                nextBlock = nextBlock.getRelative(face);
                if (shouldSplinter(nextBlock)) {
                    blocks.add(nextBlock);
                }
            }
            return blocks;
        }

        private static boolean positionMatchesXZ(final BlockState state, final Block block) {
            return state.getX() == block.getX() && state.getZ() == block.getZ();
        }

        private static boolean materialMatches(final BlockState state, final Block block) {
            return state.getType() == block.getType();
        }

        private static boolean materialMatchesAny(final BlockState state, final Block... blocks) {
            for (final var block : blocks) {
                if (materialMatches(state, block)) {
                    return true;
                }
            }
            return false;
        }
    }

    private enum SplinterType {

        SHROOM_STEM,
        SHROOM_BLOCK,
        GENERIC_TRUNK,
        GENERIC_BRANCH,
        MEGA_TRUNK,
        MEGA_BRANCH,
        ACACIA,
        CHERRY,
        MANGROVE,
        INCOMPATIBLE;

        private static SplinterType get(final Block block) {
            // FIXME
            final var material = block.getType();
            return switch (material) {
                case MUSHROOM_STEM -> SHROOM_STEM;
                case BROWN_MUSHROOM_BLOCK, RED_MUSHROOM_BLOCK -> SHROOM_BLOCK;
                default -> {
                    if (Tag.LOGS.isTagged(material) && block.getBlockData() instanceof Orientable orientable) {
                        yield orientable.getAxis() == Axis.Y ? GENERIC_TRUNK : GENERIC_BRANCH;
                    } else {
                        yield INCOMPATIBLE;
                    }
                }
            };
        }
    }
}