package xyz.holocons.mc.holoitemsrevamp.integration;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public final class IntegrationManager {

    private WorldGuardHook worldGuard;

    public IntegrationManager(HoloItemsRevamp plugin) {
        hookWorldGuard(plugin);
    }

    public void onLoad() {
        worldGuard.onLoad();
    }

    public void onEnable() {
        worldGuard.onEnable();
    }

    public WorldGuardHook getWorldGuard() {
        return worldGuard;
    }

    private void hookWorldGuard(HoloItemsRevamp plugin) {
        final var otherPlugin = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
        if (otherPlugin != null) {
            plugin.getLogger()
                    .info("Found " + otherPlugin.getName() + " v" + otherPlugin.getDescription().getVersion());
        }
        this.worldGuard = otherPlugin instanceof WorldGuardPlugin ? new WorldGuardIntegration() : new WorldGuardHook() {
        };
    }
}
