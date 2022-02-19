package xyz.holocons.mc.holoitemsrevamp.collection.gen1;

import java.util.List;

import com.strangeone101.holoitemsapi.CustomItem;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class AkiRosenthal extends Idol {

    private static final String name = "akirosenthal";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODBhMTQ0ZWMwMzFmMDNiM2YzZGQ0MDcwYzc4YjM0Y2M1YTRiMWI5YmExNWJkNWVhYmY1ZDAyODBkYzI1ZGJmMSJ9fX0=";

    public AkiRosenthal(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Aki Rosenthal")
                .color(TextColor.color(0xD80E89))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
