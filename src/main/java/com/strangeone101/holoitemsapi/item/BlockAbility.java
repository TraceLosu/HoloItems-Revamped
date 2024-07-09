package com.strangeone101.holoitemsapi.item;

import org.bukkit.GameMode;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
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
            final var itemStack = item.getItemStack();
            if (itemStack.getType().equals(getMaterial()) && itemStack.getAmount() == 1) {
                item.setItemStack(buildStack(null));
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
