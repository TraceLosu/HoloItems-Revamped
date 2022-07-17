package xyz.holocons.mc.holoitemsrevamp;

import com.strangeone101.holoitemsapi.Keys;
import com.strangeone101.holoitemsapi.enchantment.EnchantManager;

import com.strangeone101.holoitemsapi.recipe.CraftListener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.holocons.mc.holoitemsrevamp.collection.CollectionManager;
import xyz.holocons.mc.holoitemsrevamp.command.MainCommand;
import xyz.holocons.mc.holoitemsrevamp.integration.IntegrationManager;

public final class HoloItemsRevamp extends JavaPlugin {

    private CollectionManager collectionManager;
    private EnchantManager enchantManager;

    @Override
    public void onLoad() {
        Keys.fillKeys(this);

        IntegrationManager.onLoad(this);
    }

    @Override
    public void onEnable() {
        IntegrationManager.onEnable(this);

        try {
            this.enchantManager = new EnchantManager(this);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        this.collectionManager = new CollectionManager(this);

        getServer().getPluginManager().registerEvents(new CraftListener(this), this);

        getCommand("holoitems").setExecutor(new MainCommand(this));
        getLogger().info("HoloItems-Revamped [ON]");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public EnchantManager getEnchantManager() {
        return enchantManager;
    }
}
