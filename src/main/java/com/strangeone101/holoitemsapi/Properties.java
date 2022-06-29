package com.strangeone101.holoitemsapi;

import com.strangeone101.holoitemsapi.util.UUIDTagType;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Properties {

    public static class Owner extends Property<UUID> {

        private final NamespacedKey key;

        public Owner(NamespacedKey key) {
            this.key = key;
        }

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

    public static class Cooldown extends Property<Long> {

        private final NamespacedKey key;

        public Cooldown(NamespacedKey key) {
            this.key = key;
        }

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

    public static class Unstackable extends Property<Boolean> {

        private final NamespacedKey key;

        public Unstackable(NamespacedKey key) {
            this.key = key;
        }

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

    public static class ItemId extends Property<String> {

        private final NamespacedKey key;

        public ItemId(NamespacedKey key) {
            this.key = key;
        }

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
}
