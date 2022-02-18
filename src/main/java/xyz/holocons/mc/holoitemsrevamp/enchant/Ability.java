package xyz.holocons.mc.holoitemsrevamp.enchant;

import org.bukkit.event.Event;

public abstract interface Ability {
    public abstract <E extends Event> void run(E event);
}
