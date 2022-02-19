package xyz.holocons.mc.holoitemsrevamp.collection.stars2;

import java.util.List;

import com.strangeone101.holoitemsapi.CustomItem;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class KishidoTemma extends Idol {

    private static final String name = "kishidotemma";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmJhNTI1MjhjMTM4M2QwMzI5MzJjNzQxYTQ4ZWQyYTg3Y2JiMjJjOTllMWIzOTdkNTMyMjQ1NTE1NTU4M2MwYSJ9fX0=";

    public KishidoTemma(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Kishido Temma")
                .color(TextColor.color(0xF5ED94))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
