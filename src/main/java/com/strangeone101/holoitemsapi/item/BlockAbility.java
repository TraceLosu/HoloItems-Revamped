package com.strangeone101.holoitemsapi.item;

import org.bukkit.GameMode;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import io.papermc.paper.event.packet.PlayerChunkLoadEvent;
import org.bukkit.inventory.meta.BlockStateMeta;

public interface BlockAbility extends Keyed {

    ItemStack buildStack(Player player);

    Material getMaterial();

    default void onBlockBreak(BlockBreakEvent event, BlockState blockState) {
    }

    default void onBlockDispense(BlockDispenseEvent event, BlockState blockState) {
    }

    default void onBlockDropItem(BlockDropItemEvent event, BlockState blockState) {
        if (!event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }
        final var items = event.getItems();
        final var iterator = event.getItems().listIterator(items.size());
        while (iterator.hasPrevious()) {
            final var item = iterator.previous();
            final var oldItemStack = item.getItemStack();
            if (oldItemStack.getType().equals(getMaterial()) && oldItemStack.getAmount() == 1) {
                final var newItemStack = buildStack(null);
                if(oldItemStack.getType() == Material.SHULKER_BOX) {
                    // A lot of these are marked as nullable, but for shulkers it's not-null.
                    final var oldStackMeta = (BlockStateMeta) oldItemStack.getItemMeta();
                    final var oldStackState = (ShulkerBox) oldStackMeta.getBlockState();
                    newItemStack.editMeta(BlockStateMeta.class, newStackMeta -> {
                        final var newStackState = (ShulkerBox) newStackMeta.getBlockState();
                        newStackState.getInventory()
                            .setContents(oldStackState.getInventory().getContents());
                        newStackMeta.setBlockState(newStackState);
                    });
                }
                item.setItemStack(newItemStack);
                return;
            }
        }
    }

    default void onBlockIgnite(BlockIgniteEvent event, BlockState blockState) {
    }

    default void onBlockPlace(BlockPlaceEvent event, BlockState blockState) {
    }

    /**
     * Activates whenever a creature spawns in the same world as this BlockAbility.
     *
     * @param event      The CreatureSpawnEvent
     * @param blockState This BlockAbility's Block. Note that this may not be in the
     *                   same location as the event.
     */
    default void onCreatureSpawn(CreatureSpawnEvent event, BlockState blockState) {
    }

    default void onInventoryClose(InventoryCloseEvent event, BlockState blockState) {
    }

    default void onPlayerInteract(PlayerInteractEvent event, BlockState blockState) {
    }

    default void onPlayerChunkLoad(PlayerChunkLoadEvent event, BlockState blockState) {
    }
}
