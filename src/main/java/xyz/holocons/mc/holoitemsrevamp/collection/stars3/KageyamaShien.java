package xyz.holocons.mc.holoitemsrevamp.collection.stars3;

import java.util.List;

import com.strangeone101.holoitemsapi.CustomItem;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class KageyamaShien extends Idol {

    private static final String name = "kageyamashien";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI1YWRjYzU0M2FkYWIzYWY2OWNkOTFjOWY2NTg3ZTljOTY1MDEzNDljMmNkYjAxMjIzMTAzYzY2NDA1M2Y5NyJ9fX0=";

    public KageyamaShien(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Kageyama Shien")
                .color(TextColor.color(0x8B69AE))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
