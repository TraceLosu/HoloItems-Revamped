package com.strangeone101.holoitemsapi;

import com.strangeone101.holoitemsapi.util.UUIDTagType;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.UUID;


public class Properties {

    private final HoloItemsRevamp plugin;
    private final Owner owner;
    private final OwnerName ownerName;
    private final Cooldown cooldown;
    private final Unstackable unstackable;
    private final ItemId itemId;
    private final Renamable renamable;
    private final Durability durability;

    public Properties(HoloItemsRevamp plugin) {
        this.plugin = plugin;
        this.owner = new Owner();
        this.ownerName = new OwnerName();
        this.cooldown = new Cooldown();
        this.unstackable = new Unstackable();
        this.itemId = new ItemId();
        this.renamable = new Renamable();
        this.durability = new Durability();
    }

    public class Owner extends Property<UUID> {

        private final NamespacedKey key = new NamespacedKey(plugin, "itemowner");

        @Override
        public boolean has(PersistentDataContainer data) {
            return data.has(getKey(), UUIDTagType.TYPE);
        }

        @Override
        public UUID get(PersistentDataContainer data) {
            return data.get(getKey(), UUIDTagType.TYPE);
        }

        @Override
        public void set(PersistentDataContainer data, UUID value) {
            data.set(getKey(), UUIDTagType.TYPE, value);
        }

        @Override
        public String getPropertyName() {
            return "Owner";
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return key;
        }
    }

    public class OwnerName extends Property<String> {

        private final NamespacedKey key = new NamespacedKey(plugin, "itemowername");

        @Override
        public boolean has(PersistentDataContainer data) {
            return data.has(getKey(), PersistentDataType.STRING);
        }

        @Override
        public String get(PersistentDataContainer data) {
            return data.get(getKey(), PersistentDataType.STRING);
        }

        @Override
        public void set(PersistentDataContainer data, String value) {
            data.set(getKey(), PersistentDataType.STRING, value);
        }

        @Override
        public String getPropertyName() {
            return "Owner Name";
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return key;
        }
    }

    public class Cooldown extends Property<Long> {

        private final NamespacedKey key = new NamespacedKey(plugin, "itemcooldown");

        @Override
        public boolean has(PersistentDataContainer data) {
            return data.has(getKey(), PersistentDataType.LONG);
        }

        @Override
        public Long get(PersistentDataContainer data) {
            return data.getOrDefault(getKey(), PersistentDataType.LONG, 0L);
        }

        @Override
        public void set(PersistentDataContainer data, Long value) {
            data.set(getKey(), PersistentDataType.LONG, value);
        }

        @Override
        public String getPropertyName() {
            return "Cooldown";
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return key;
        }
    }

    public class Unstackable extends Property<Boolean> {

        private final NamespacedKey key = new NamespacedKey(plugin, "unstackable");


        @Override
        public boolean has(PersistentDataContainer data) {
            return data.has(getKey(), PersistentDataType.INTEGER);
        }

        @Override
        public Boolean get(PersistentDataContainer data) {
            return has(data);
        }

        @Override
        public void set(PersistentDataContainer data, Boolean value) {
            if (value) {
                data.set(getKey(), PersistentDataType.INTEGER, Bukkit.getCurrentTick());
            } else {
                data.remove(getKey());
            }
        }

        @Override
        public String getPropertyName() {
            return "Unstackable";
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return key;
        }
    }

    public class ItemId extends Property<String> {

        private final NamespacedKey key = new NamespacedKey(plugin, "customitemid");

        @Override
        public boolean has(PersistentDataContainer data) {
            return data.has(getKey(), PersistentDataType.STRING);
        }

        @Override
        public String get(PersistentDataContainer data) {
            return data.get(getKey(), PersistentDataType.STRING);
        }

        @Override
        public void set(PersistentDataContainer data, String value) {
            data.set(getKey(), PersistentDataType.STRING, value);
        }

        @Override
        public String getPropertyName() {
            return "Owner Name";
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return key;
        }
    }

    public class Renamable extends Property<Integer> {

        private final NamespacedKey key = new NamespacedKey(plugin, "rename");

        @Override
        public boolean has(PersistentDataContainer data) {
            return data.has(getKey(), PersistentDataType.INTEGER);
        }

        @Override
        public Integer get(PersistentDataContainer data) {
            return data.getOrDefault(getKey(), PersistentDataType.INTEGER, 0);
        }

        @Override
        public void set(PersistentDataContainer data, Integer value) {
            data.set(getKey(), PersistentDataType.INTEGER, value);
        }

        @Override
        public String getPropertyName() {
            return "Renamed";
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return key;
        }
    }

    public class Durability extends Property<Integer> {

        private final NamespacedKey key = new NamespacedKey(plugin, "itemdurability");

        @Override
        public boolean has(PersistentDataContainer data) {
            return data.has(getKey(), PersistentDataType.INTEGER);
        }

        @Override
        public Integer get(PersistentDataContainer data) {
            return data.getOrDefault(getKey(), PersistentDataType.INTEGER, 0);
        }

        @Override
        public void set(PersistentDataContainer data, Integer value) {
            data.set(getKey(), PersistentDataType.INTEGER, value);
        }

        @Override
        public String getPropertyName() {
            return "Durability";
        }

        @Override
        public @NotNull NamespacedKey getKey() {
            return key;
        }
    }

    public Owner getOwner() {
        return owner;
    }

    public OwnerName getOwnerName() {
        return ownerName;
    }

    public Cooldown getCooldown() {
        return cooldown;
    }

    public Unstackable getUnstackable() {
        return unstackable;
    }

    public ItemId getItemId() {
        return itemId;
    }

    public Renamable getRenamable() {
        return renamable;
    }

    public Durability getDurability() {
        return durability;
    }
}
