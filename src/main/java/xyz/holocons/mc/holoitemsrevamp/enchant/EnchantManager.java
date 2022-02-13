package xyz.holocons.mc.holoitemsrevamp.enchant;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.enchantments.Magnet;

import java.lang.reflect.Field;
import java.util.Set;

public class EnchantManager {

    private final HoloItemsRevamp plugin;
    private final Set<CustomEnchant> customEnchants;
    private final EnchantListener enchantListener;

    public EnchantManager(HoloItemsRevamp plugin) {
        this.plugin = plugin;
        this.enchantListener = new EnchantListener(plugin);
        customEnchants = Set.of(
            new Magnet(plugin)
        );
        customEnchants.forEach(this::registerEnchantment);
    }

    public CustomEnchant getCustomEnchant(String name) {
        var result = customEnchants.stream().filter(enchant -> enchant.getName().equalsIgnoreCase(name)).findAny();
        return result.orElse(null);
    }

    private void registerEnchantment(Enchantment enchantment) {
        try {
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            Bukkit.getLogger().severe("[HoloItems] Failed to register enchantment " + enchantment.getName());
            e.printStackTrace();
        }
    }
}
