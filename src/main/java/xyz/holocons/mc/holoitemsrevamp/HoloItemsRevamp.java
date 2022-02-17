package xyz.holocons.mc.holoitemsrevamp;

import com.strangeone101.holoitemsapi.HoloItemsAPI;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.holocons.mc.holoitemsrevamp.collection.CollectionManager;
import xyz.holocons.mc.holoitemsrevamp.command.MainCommand;
import xyz.holocons.mc.holoitemsrevamp.enchant.EnchantManager;

public final class HoloItemsRevamp extends JavaPlugin {

    CollectionManager collectionManager;
    EnchantManager enchantManager;

    @Override
    public void onEnable() {
        HoloItemsAPI.setup(this);
        enchantManager = new EnchantManager(this);
        collectionManager = new CollectionManager(this);
        getCommand("holoitems").setExecutor(new MainCommand(this));
        getCommand("holoitems").setTabCompleter(new MainCommand(this));
        this.getLogger().info("HoloItems-Revamped [ON]");
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
