package xyz.holocons.mc.holoitemsrevamp.ability;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public interface BlockPlace {

    void run(BlockPlaceEvent event, ItemStack itemStack);
}
