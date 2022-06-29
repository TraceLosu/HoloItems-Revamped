package xyz.holocons.mc.holoitemsrevamp;

import com.strangeone101.holoitemsapi.Keys;
import com.strangeone101.holoitemsapi.enchantment.EnchantManager;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.holocons.mc.holoitemsrevamp.collection.CollectionManager;
import xyz.holocons.mc.holoitemsrevamp.command.MainCommand;

import java.util.HashMap;

public final class HoloItemsRevamp extends JavaPlugin {

    private CollectionManager collectionManager;
    private EnchantManager enchantManager;

    @Override
    public void onEnable() {
        buildKeys();

        try {
            enchantManager = new EnchantManager(this);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        collectionManager = new CollectionManager(this);

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

    private void buildKeys() {
        var keyMap = new HashMap<String, NamespacedKey>(7);

        keyMap.put("owner", new NamespacedKey(this, "owner"));
        keyMap.put("owner_name", new NamespacedKey(this, "owner_name"));
        keyMap.put("cooldown", new NamespacedKey(this, "cooldown"));
        keyMap.put("durability", new NamespacedKey(this, "durability"));
        keyMap.put("unstackable", new NamespacedKey(this, "unstackable"));
        keyMap.put("renamable", new NamespacedKey(this, "renamable"));
        keyMap.put("item_id", new NamespacedKey(this, "item_id"));

        Keys.fillKeys(keyMap);
    }
}
