package xyz.holocons.mc.holoitemsrevamp.enchant;

import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.enchant.enchantment.Magnet;
import xyz.holocons.mc.holoitemsrevamp.enchant.enchantment.TideRider;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.function.Predicate;

public class EnchantManager {

    private final HoloItemsRevamp plugin;
    private final Set<CustomEnchantment> customEnchantments;

    public EnchantManager(HoloItemsRevamp plugin) throws ReflectiveOperationException {
        try {
            final Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new ReflectiveOperationException(e);
        }
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new EnchantListener(plugin, this), plugin);
        customEnchantments = Set.of(
            new Magnet(plugin),
            new TideRider(plugin)
        );
        customEnchantments.forEach(Enchantment::registerEnchantment);
    }

    /**
     * Gets a custom enchantment from the plugin by class.
     * @param enchantmentCls The class of the enchantment
     * @return The CustomEnchantment, or null if it could not find one
     */
    @Nullable
    public <E extends CustomEnchantment> E getCustomEnchantment(@NotNull Class<E> enchantmentCls) {
        Predicate<CustomEnchantment> matchesClass = enchantment -> enchantmentCls.isInstance(enchantment);
        return enchantmentCls.cast(customEnchantments.stream().filter(matchesClass).findAny().orElse(null));
    }
}
