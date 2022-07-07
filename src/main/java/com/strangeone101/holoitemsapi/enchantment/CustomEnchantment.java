package com.strangeone101.holoitemsapi.enchantment;

import io.papermc.paper.enchantments.EnchantmentRarity;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    public CustomEnchantment(Plugin plugin, String key) {
        super(new NamespacedKey(plugin, key));
    }

    /**
     * Gets the CustomEnchantment at the specified key.
     * @param key The NamespacedKey of the enchantment to fetch
     * @return Resulting CustomEnchantment, or null if not found
     */
    @Nullable
    public static final CustomEnchantment getByKey(@Nullable NamespacedKey key) {
        return Enchantment.getByKey(key) instanceof CustomEnchantment customEnchantment ? customEnchantment : null;
    }

    /**
     * Returns the multiplier used to add levels when combining the enchantment. This method is simillar to vanilla
     * anvil mechanics
     * @see <a href="https://minecraft.fandom.com/wiki/Anvil_mechanics#Costs_for_combining_enchantments">Anvil Mechanics</a>
     * @return The multiplier for combining this enchantment
     */
    public abstract int getLevelMultiplier();

    @NotNull
    @Override
    public String getName() {
        return getKey().getKey();
    }

    @Override
    public @NotNull String translationKey() {
        return "";
    }

    @Override
    public int getStartLevel() {
        return 1;
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
        return EnchantmentRarity.VERY_RARE;
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
