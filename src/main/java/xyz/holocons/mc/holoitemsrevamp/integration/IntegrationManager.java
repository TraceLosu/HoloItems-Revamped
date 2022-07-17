package xyz.holocons.mc.holoitemsrevamp.integration;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public final class IntegrationManager {

    private static WorldGuardHook worldGuard;

    private IntegrationManager() {
    }

    public static void onLoad(HoloItemsRevamp plugin) {
        hookWorldGuard(plugin);
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

    private static void hookWorldGuard(HoloItemsRevamp plugin) {
        final var otherPlugin = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
        if (otherPlugin != null) {
            plugin.getLogger()
                    .info("Found " + otherPlugin.getName() + " v" + otherPlugin.getDescription().getVersion());
        }
        worldGuard = otherPlugin instanceof WorldGuardPlugin ? new WorldGuardIntegration() : new WorldGuardHook() {
        };
    }
}
