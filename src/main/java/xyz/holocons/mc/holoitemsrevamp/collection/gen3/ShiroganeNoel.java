package xyz.holocons.mc.holoitemsrevamp.collection.gen3;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class ShiroganeNoel extends Idol {

    private static final String name = "shiroganenoel";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjYzMjQ2MTQ0NWU0YmZmOTIwMzlkZmNiODljNWIxN2ZkYzJkNzNhNWQxMTIwYjFjOTI3MWQ4NjQ0YTRlOGMwOCJ9fX0=";

    public ShiroganeNoel(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Shirogane Noel")
                .color(TextColor.color(0x8A939E))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
