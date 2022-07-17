package xyz.holocons.mc.holoitemsrevamp.integration;

import java.util.Map;

import org.bukkit.Location;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import xyz.holocons.mc.holoitemsrevamp.ability.Ability;
import xyz.holocons.mc.holoitemsrevamp.ability.BlockBreak;
import xyz.holocons.mc.holoitemsrevamp.ability.BlockPlace;
import xyz.holocons.mc.holoitemsrevamp.ability.PlayerDeath;
import xyz.holocons.mc.holoitemsrevamp.ability.PlayerInteract;
import xyz.holocons.mc.holoitemsrevamp.ability.ProjectileLaunch;

public class WorldGuardIntegration implements WorldGuardHook {

    public static final Map<Class<? extends Ability>, Flag<?>> ABILITY_FLAGS = Map.ofEntries(
            Map.entry(BlockBreak.class, new StateFlag("holoitems-block-break", true)),
            Map.entry(BlockPlace.class, new StateFlag("holoitems-block-place", true)),
            Map.entry(PlayerDeath.class, new StateFlag("holoitems-player-death", true)),
            Map.entry(PlayerInteract.class, new StateFlag("holoitems-player-interact", true)),
            Map.entry(ProjectileLaunch.class, new StateFlag("holoitems-projectile-launch", true)));

    private RegionContainer regionContainer;

    @Override
    public void onLoad() {
        WorldGuard.getInstance().getFlagRegistry().registerAll(ABILITY_FLAGS.values());
    }

    @Override
    public void onEnable() {
        this.regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
    }

    @Override
    public boolean testAbilityAllowed(Location location, Class<? extends Ability> ability) {
        final var value = regionContainer.createQuery()
                .queryValue(BukkitAdapter.adapt(location), null, ABILITY_FLAGS.get(ability));
        return value != StateFlag.State.DENY;
    }
}
