package xyz.holocons.mc.holoitemsrevamp.collection.en1;

import java.util.List;

import com.strangeone101.holoitemsapi.CustomItem;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class TakanashiKiara extends Idol {

    private static final String name = "takanashikiara";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGRmNzdiZDFlODQ1ZDY5ZDZiZWRjMmZlMGNiZTJhMmJiNTFmYzY4MGQ1MWI5ZWM4ZDMwNjY1MTQyMGMzODMwMCJ9fX0=";

    public TakanashiKiara(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Takanashi Kiara")
                .color(TextColor.color(0xDC3907))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
