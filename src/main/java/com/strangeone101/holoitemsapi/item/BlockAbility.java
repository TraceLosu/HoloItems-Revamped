package com.strangeone101.holoitemsapi.item;

import org.bukkit.GameMode;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
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
        final var items = event.getItems();
        if (items.isEmpty() || !event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }
        final var itemStack = items.get(items.size() - 1).getItemStack();
        if (itemStack.getType().equals(getMaterial()) && itemStack.getAmount() == 1) {
            items.get(items.size() - 1).setItemStack(buildStack(null));
        }
    }

    default void onBlockPlace(BlockPlaceEvent event, BlockState blockState) {
    }

    default void onInventoryClose(InventoryCloseEvent event, BlockState blockState) {
    }

    default void onPlayerChunkLoad(PlayerChunkLoadEvent event, BlockState blockState) {
    }
}
