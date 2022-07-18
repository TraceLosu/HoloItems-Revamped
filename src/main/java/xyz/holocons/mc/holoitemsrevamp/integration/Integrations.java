package xyz.holocons.mc.holoitemsrevamp.integration;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public final class Integrations {

    public static final WorldGuardHook WORLDGUARD;

    static {
        final var thisPlugin = Arrays.stream(Bukkit.getPluginManager().getPlugins())
                .filter(plugin -> plugin instanceof HoloItemsRevamp).findFirst().get();
        WORLDGUARD = getPlugin(thisPlugin, "WorldGuard") instanceof WorldGuardPlugin
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

    public static void onLoad() {
        WORLDGUARD.onLoad();
    }

    public static void onEnable() {
        WORLDGUARD.onEnable();
    }
}
