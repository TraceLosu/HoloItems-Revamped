package xyz.holocons.mc.holoitemsrevamp.collection.stars1;

import java.util.List;

import com.strangeone101.holoitemsapi.CustomItem;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class Rikka extends Idol {

    private static final String name = "rikka";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFhYmE3ZTZlNjBiY2M4MDgyY2QyNTExZjZkOTZjODJkYTMxODRhMGJiMmI2MmYxN2QyMDBkNDhjOTZiM2U3MyJ9fX0=";

    public Rikka(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Rikka")
                .color(TextColor.color(0xFED2D5))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
