package com.strangeone101.holoitemsapi;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry for managing all custom items
 */
public class CustomItemManager {

    private int NEXT_ID = 2300;
    private final int INVALID_ID = 404;

    private final Map<String, CustomItem> CUSTOM_ITEMS = new HashMap<>();

    private final Properties properties;

    public CustomItemManager(Properties pluginProperties) {
        this.properties = pluginProperties;
    }

    /**
     * Register a custom item
     * @param item The item
     */
    public void register(CustomItem item) {
        CUSTOM_ITEMS.put(item.getInternalName(), item);

        if (item.getInternalID() == 0) {
            item.setInternalID(NEXT_ID);

            NEXT_ID++;
            if (NEXT_ID == INVALID_ID) NEXT_ID++;
        }
    }

    /**
     * Get a custom item from the item identifier
     * @param id The item identifier
     * @return The custom item
     */
    public CustomItem getCustomItem(String id) {
        return CUSTOM_ITEMS.get(id);
    }

    /**
     * Is this a custom item?
     * @param stack The itemstack
     * @return True if it's a custom item
     */
    public boolean isCustomItem(ItemStack stack) {
        return stack != null &&
            stack.hasItemMeta() &&
            properties.getItemId().has(stack.getItemMeta().getPersistentDataContainer());
    }

    /**
     * Get the CustomItem from the provided stack
     * @param stack The itemstack
     * @return The custom item
     */
    public CustomItem getCustomItem(ItemStack stack) {
        if (isCustomItem(stack)) {
            String id = properties.getItemId().get(stack.getItemMeta().getPersistentDataContainer());
            return getCustomItem(id);
        }
        return null;
    }

    /**
     * Get all custom items
     * @return The many many items
     */
    public Map<String, CustomItem> getCustomItems() {
        return CUSTOM_ITEMS;
    }

}
