package com.strangeone101.holoitemsapi.enchantment;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 * An interface to allow custom items to be used for enchanting
 */
public interface Enchantable {

    /**
     * Returns the enchantment the custom item uses.
     * @return The enchantment.
     */
    public Enchantment getEnchantment();

    /**
     * Applies the enchantment to the itemstack.
     * @param itemStack The item stack to apply the enchantment to.
     * @return A clone of the item stack with the enchantment applied.
     */
    public ItemStack applyEnchantment(ItemStack itemStack);

}
