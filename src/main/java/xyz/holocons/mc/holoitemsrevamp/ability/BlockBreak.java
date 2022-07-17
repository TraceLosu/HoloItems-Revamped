package xyz.holocons.mc.holoitemsrevamp.ability;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public interface BlockBreak extends Ability {

    void run(BlockBreakEvent event, ItemStack itemStack);
}
