package xyz.holocons.mc.holoitemsrevamp.enchant;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public abstract interface Ability {
    public abstract <E extends Event> void run(E event, ItemStack itemStack);
}
