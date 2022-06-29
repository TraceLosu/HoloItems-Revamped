package xyz.holocons.mc.holoitemsrevamp.collection.gen6;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class TakaneLui extends Idol {

    private static final String name = "takanelui";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzFmYWMzNTg1YWMyOTA1OTIxNmEwM2UwMTIzNGQ1MTg5MDU2ZGQ4YWI3ODJiMWYyNGU0OTlhY2M3NWUwMDUyZSJ9fX0=";

    public TakaneLui(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Takane Lui")
                .color(TextColor.color(0xDA9190))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
