package xyz.holocons.mc.holoitemsrevamp.collection.misc;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class Achan extends Idol {

    private static final String name = "achan";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTA0OGQ2ZGFlZWI4MDgyNjlhZmY1MzMzNzI0YWY4Y2Y4NTY1NmI5MjM2MGI0NTM1M2FmZjcyZWNhMmIwYzZkNiJ9fX0=";

    public Achan(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("A-chan")
                .color(TextColor.color(0x534A92))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
