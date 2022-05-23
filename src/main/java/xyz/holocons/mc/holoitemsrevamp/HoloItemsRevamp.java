package xyz.holocons.mc.holoitemsrevamp;

import com.strangeone101.holoitemsapi.CustomItemManager;
import com.strangeone101.holoitemsapi.Properties;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.holocons.mc.holoitemsrevamp.collection.CollectionManager;
import xyz.holocons.mc.holoitemsrevamp.command.MainCommand;
import xyz.holocons.mc.holoitemsrevamp.enchant.EnchantManager;

public final class HoloItemsRevamp extends JavaPlugin {

    private CollectionManager collectionManager;
    private EnchantManager enchantManager;
    private Properties properties;
    private CustomItemManager customItemManager;


    @Override
    public void onEnable() {
        try {
            enchantManager = new EnchantManager(this);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        collectionManager = new CollectionManager(this);

        properties = new Properties(this);

        customItemManager = new CustomItemManager(properties);

        getCommand("holoitems").setExecutor(new MainCommand(this));
        this.getLogger().info("HoloItems-Revamped [ON]");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Properties getProperties() {
        return properties;
    }

    public CustomItemManager getCustomItemManager() {
        return customItemManager;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public EnchantManager getEnchantManager() {
        return enchantManager;
    }
}
