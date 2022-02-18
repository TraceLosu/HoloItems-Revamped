package xyz.holocons.mc.holoitemsrevamp.enchant;

import org.bukkit.enchantments.Enchantment;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.enchant.enchantment.Magnet;
import xyz.holocons.mc.holoitemsrevamp.enchant.enchantment.TideRider;

import java.lang.reflect.Field;
import java.util.Set;

public class EnchantManager {

    private final HoloItemsRevamp plugin;
    private final Set<CustomEnchantment> customEnchantments;

    public EnchantManager(HoloItemsRevamp plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new EnchantListener(plugin, this), plugin);
        customEnchantments = Set.of(
            new Magnet(plugin),
            new TideRider(plugin)
        );
        customEnchantments.forEach(this::registerEnchantment);
    }

    /**
     * Gets a custom enchantment from the plugin by name.
     * @param name The name of the enchantment
     * @return The CustomEnchantment class corresponding to the name, or null if it could not find one.
     */
    public CustomEnchantment getCustomEnchantment(String name) {
        var result = customEnchantments.stream().filter(enchant -> enchant.getName().equalsIgnoreCase(name)).findAny();
        return result.orElse(null);
    }

    private void registerEnchantment(Enchantment enchantment) {
        try {
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            plugin.getLogger().severe("Failed to register enchantment " + enchantment.getKey());
            e.printStackTrace();
        }
    }
}
