package com.strangeone101.holoitemsapi;

import org.bukkit.NamespacedKey;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public class Keys {

    //Generic Keys

    /** The key for storing custom item IDs. Should be <strong>STRING</strong> */
    public final NamespacedKey CUSTOM_ITEM_ID;
    /** The key for storing custom item owner UUIDs. Should be <strong>UUID</strong> */
    public final NamespacedKey CUSTOM_ITEM_OWNER;
    /** The key for storing custom item owner UUIDs. Should be <strong>STRING</strong> */
    public final NamespacedKey CUSTOM_ITEM_OWNER_NAME;
    /** The key for storing when the item can be used next. Should be <strong>LONG</strong> */
    public final NamespacedKey CUSTOM_ITEM_COOLDOWN;
    /** The key for storing custom durability. Should be <strong>INT</strong> */
    public final NamespacedKey CUSTOM_ITEM_DURABILITY;
    /** The key for making it unstackable. Should be <strong>INT</strong> */
    public final NamespacedKey CUSTOM_ITEM_UNSTACK;
    /** The key for making it renamable. Should be <strong>INT</strong> */
    public final NamespacedKey CUSTOM_ITEM_RENAME;

    public Keys(HoloItemsRevamp plugin) {
        CUSTOM_ITEM_ID = new NamespacedKey(plugin, "customitemid");
        CUSTOM_ITEM_OWNER = new NamespacedKey(plugin, "itemowner");
        CUSTOM_ITEM_OWNER_NAME = new NamespacedKey(plugin, "itemownername");
        CUSTOM_ITEM_COOLDOWN = new NamespacedKey(plugin, "itemcooldown");
        CUSTOM_ITEM_DURABILITY = new NamespacedKey(plugin, "itemdurability");
        CUSTOM_ITEM_UNSTACK = new NamespacedKey(plugin, "unstackable");
        CUSTOM_ITEM_RENAME = new NamespacedKey(plugin, "rename");
    }

}
