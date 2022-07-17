package xyz.holocons.mc.holoitemsrevamp.integration;

import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public final class Integrations {

    private static WorldGuardHook worldGuard;

    private Integrations() {
    }

    private static Plugin getPlugin(HoloItemsRevamp plugin, String otherPluginName) {
        final var otherPlugin = plugin.getServer().getPluginManager().getPlugin(otherPluginName);
        if (otherPlugin != null) {
            plugin.getLogger()
                    .info("Found " + otherPlugin.getName() + " v" + otherPlugin.getDescription().getVersion());
        }
        return otherPlugin;
    }

    public static void onLoad(HoloItemsRevamp plugin) {
        worldGuard = getPlugin(plugin, "WorldGuard") instanceof WorldGuardPlugin
                ? new WorldGuardHook.Integration()
                : new WorldGuardHook.Stub();
        worldGuard.onLoad();
    }

    public static void onEnable(HoloItemsRevamp plugin) {
        worldGuard.onEnable();
    }

    public static WorldGuardHook getWorldGuard() {
        if (worldGuard == null) {
            throw new NullPointerException("WorldGuard was not hooked yet!");
        }
        return worldGuard;
    }
}
