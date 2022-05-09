package com.strangeone101.holoitemsapi;

import net.kyori.adventure.key.Key;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

public abstract class Property<T> implements Keyed {

    public abstract boolean has(PersistentDataContainer data);

    public abstract T get(PersistentDataContainer data);

    public abstract void set(PersistentDataContainer data, T value);

    public abstract String getPropertyName();

    @Override
    public abstract @NotNull NamespacedKey getKey();

    @Override
    public int hashCode() {
        return getPropertyName().hashCode();
    }
}
