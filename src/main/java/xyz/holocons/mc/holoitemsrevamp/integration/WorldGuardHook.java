package xyz.holocons.mc.holoitemsrevamp.integration;

import org.bukkit.Location;

import xyz.holocons.mc.holoitemsrevamp.ability.Ability;

public interface WorldGuardHook extends Hook {

    default boolean testAbilityAllowed(Location location, Class<? extends Ability> ability) {
        return true;
    }
}
