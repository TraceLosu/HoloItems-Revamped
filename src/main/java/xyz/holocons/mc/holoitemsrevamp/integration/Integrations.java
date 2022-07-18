package xyz.holocons.mc.holoitemsrevamp.integration;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public final class Integrations {

    public static final WorldGuardHook WORLD_GUARD;

    static {
        final var thisPlugin = Arrays.stream(Bukkit.getPluginManager().getPlugins())
                .filter(plugin -> plugin instanceof HoloItemsRevamp).findFirst().get();
        WORLD_GUARD = getPlugin(thisPlugin, "WorldGuard") instanceof WorldGuardPlugin
                ? new WorldGuardHook.Integration()
                : new WorldGuardHook.Stub();
    }

    private Integrations() {
    }

    private static Plugin getPlugin(Plugin thisPlugin, String otherPluginName) {
        final var otherPlugin = thisPlugin.getServer().getPluginManager().getPlugin(otherPluginName);
        if (otherPlugin != null) {
            thisPlugin.getLogger()
                    .info("Found " + otherPlugin.getName() + " v" + otherPlugin.getDescription().getVersion());
        }
        return otherPlugin;
    }

    public static void onLoad(HoloItemsRevamp plugin) {
        WORLD_GUARD.onLoad();
    }

    public static void onEnable(HoloItemsRevamp plugin) {
        WORLD_GUARD.onEnable();
    }
}
