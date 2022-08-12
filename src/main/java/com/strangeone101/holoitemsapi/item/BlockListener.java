package com.strangeone101.holoitemsapi.item;

import io.papermc.paper.event.packet.PlayerChunkLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.BlockInventoryHolder;

import com.strangeone101.holoitemsapi.tracking.TrackingManager;

import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public class BlockListener implements Listener {

    private final TrackingManager trackingManager;

    public BlockListener(HoloItemsRevamp plugin) {
        this.trackingManager = plugin.getTrackingManager();
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(final BlockBreakEvent event) {
        if (!trackingManager.isTracked(event.getBlock())) {
            return;
        }

        trackingManager.getBlockAbility(event.getBlock())
                .onBlockBreak(event, event.getBlock().getState());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBurn(final BlockBurnEvent event) {
        trackingManager.untrack(event.getBlock());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockDispense(BlockDispenseEvent event) {
        if (!trackingManager.isTracked(event.getBlock())) {
            return;
        }

        trackingManager.getBlockAbility(event.getBlock())
                .onBlockDispense(event, event.getBlock().getState());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockDropItem(BlockDropItemEvent event) {
        final var ability = trackingManager.untrack(event.getBlock());
        if (ability == null) {
            return;
        }

        ability.onBlockDropItem(event, event.getBlock().getState());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockExplode(final BlockExplodeEvent event) {
        event.blockList().removeIf(trackingManager::isTracked);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockFade(final BlockFadeEvent event) {
        trackingManager.untrack(event.getBlock());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace(final BlockPlaceEvent event) {
        final var itemStack = event.getItemInHand();

        if (!(CustomItemManager.getCustomItem(itemStack) instanceof BlockAbility ability)) {
            return;
        }

        trackingManager.track(event.getBlockPlaced(), ability);
        ability.onBlockPlace(event, event.getBlock().getState());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityExplode(final EntityExplodeEvent event) {
        event.blockList().removeIf(trackingManager::isTracked);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof BlockInventoryHolder blockInventoryHolder)
                || !trackingManager.isTracked(blockInventoryHolder.getBlock())) {
            return;
        }

        trackingManager.getBlockAbility(blockInventoryHolder.getBlock())
                .onInventoryClose(event, blockInventoryHolder.getBlock().getState());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChunkLoad(PlayerChunkLoadEvent event) {
        trackingManager.getTrackedBlocks(event.getWorld().getUID(), event.getChunk().getChunkKey())
                .forEach(entry -> entry.getValue().onPlayerChunkLoad(event, entry.getKey().blockState()));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPistonExtend(BlockPistonExtendEvent event) {
        for (final var block : event.getBlocks()) {
            if (trackingManager.isTracked(block)) {
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPistonRetract(BlockPistonRetractEvent event) {
        if (!event.isSticky()) {
            return;
        }

        for (final var block : event.getBlocks()) {
            if (trackingManager.isTracked(block)) {
                event.setCancelled(true);
                return;
            }
        }
    }
}
