package xyz.holocons.mc.holoitemsrevamp.ability;

import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import xyz.holocons.mc.holoitemsrevamp.enchant.Ability;

public abstract interface BlockBreak extends Ability {
    @Override
    default public <E extends Event> void run(E event, ItemStack itemStack) {
        run((BlockBreakEvent) event, itemStack);
    }

    public abstract void run(BlockBreakEvent event, ItemStack itemStack);
}
