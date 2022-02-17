package xyz.holocons.mc.holoitemsrevamp.enchant;

import io.papermc.paper.enchantments.EnchantmentRarity;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.Set;

/**
 * An abstract class to implement custom enchantments. Unsurprisingly, The Bukkit API has no capabilities of adding custom
 * enchantments to the enchanting table. Most of these methods here are practically useless because they were meant for
 * enchantments that would appear in the enchantment table. However, that will not happen because, again, Bukkit has no capabilities
 * of adding it. So, most of the Enchantment methods are set to <em>default</em> values.
 *
 * Some methods, like {@link Enchantment#displayName(int)} and {@link Enchantment#canEnchantItem(ItemStack)} aren't implemented.
 * While they also do nothing, they might help in setting enchantment lores, and validating anvil crafts.
 */
public abstract class CustomEnchantment extends Enchantment {

    public CustomEnchantment(HoloItemsRevamp plugin, String namespace) {
        super(new NamespacedKey(plugin, namespace));
    }

    /**
     * Gets the cost of enchanting an itemstack with the enchantment.
     * @param itemStack The itemstack to check for.
     * @return Amount of levels to enchant the itemstack.
     */
    public abstract int getItemStackCost(ItemStack itemStack);

    @NotNull
    @Override
    public abstract String getName();

    @Override
    public @NotNull String translationKey() {
        return "";
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public @NotNull EnchantmentRarity getRarity() {
        return null;
    }

    @Override
    public float getDamageIncrease(int level, @NotNull EntityCategory entityCategory) {
        return 0;
    }

    @Override
    public @NotNull Set<EquipmentSlot> getActiveSlots() {
        return null;
    }
}
