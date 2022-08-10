package com.strangeone101.holoitemsapi.tracking;

import com.strangeone101.holoitemsapi.item.BlockAbility;
import com.strangeone101.holoitemsapi.item.CustomItemManager;
import io.papermc.paper.event.packet.PlayerChunkLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.BlockInventoryHolder;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public class TrackingListener implements Listener {

    private final HoloItemsRevamp plugin;

    public TrackingListener(HoloItemsRevamp plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace(final BlockPlaceEvent event) {
        final var itemStack = event.getItemInHand();

        if (!(CustomItemManager.getCustomItem(itemStack) instanceof BlockAbility ability))
            return;

        plugin.getTrackingManager().track(event.getBlockPlaced(), ability);
        ability.onBlockPlace(event, event.getBlock().getState());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(final BlockBreakEvent event) {

        var ability = plugin.getTrackingManager().untrack(event.getBlock());
        if (ability == null)
            return;
        ability.onBlockBreak(event, event.getBlock().getState());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockDispense(BlockDispenseEvent event) {
        if (!plugin.getTrackingManager().isTracked(event.getBlock())) {
            return;
        }

        plugin.getTrackingManager().getBlockAbility(event.getBlock())
            .onBlockDispense(event, event.getBlock().getState());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder(true) instanceof BlockInventoryHolder blockInventoryHolder)
            || !plugin.getTrackingManager().isTracked(blockInventoryHolder.getBlock())) {
            return;
        }

        plugin.getTrackingManager().getBlockAbility(blockInventoryHolder.getBlock())
            .onInventoryClose(event, blockInventoryHolder.getBlock().getState());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChunkLoad(PlayerChunkLoadEvent event) {
        plugin.getTrackingManager().getTrackedBlocks(event.getWorld().getUID(), event.getChunk().getChunkKey())
            .forEach(entry -> entry.getValue().onPlayerChunkLoad(event, entry.getKey().blockState()));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockExplode(final BlockExplodeEvent event) {
        event.blockList().forEach(plugin.getTrackingManager()::untrack);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBurn(final BlockBurnEvent event) {
        plugin.getTrackingManager().untrack(event.getBlock());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockFade(final BlockFadeEvent event) {
        plugin.getTrackingManager().untrack(event.getBlock());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPistonExtend(BlockPistonExtendEvent event) {
        var hasCustomBlock = event.getBlocks().stream().anyMatch(block -> plugin.getTrackingManager().isTracked(block));

        if (hasCustomBlock)
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPistonRetract(BlockPistonRetractEvent event) {
        if (!event.isSticky())
            return;

        var hasCustomBlock = event.getBlocks().stream().anyMatch(block -> plugin.getTrackingManager().isTracked(block));

        if (hasCustomBlock)
            event.setCancelled(true);
    }
}
