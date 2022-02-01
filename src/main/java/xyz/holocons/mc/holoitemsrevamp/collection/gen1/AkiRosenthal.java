package xyz.holocons.mc.holoitemsrevamp.collection.gen1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class AkiRosenthal extends Idol {

    private static final String name = "akirosenthal";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODBhMTQ0ZWMwMzFmMDNiM2YzZGQ0MDcwYzc4YjM0Y2M1YTRiMWI5YmExNWJkNWVhYmY1ZDAyODBkYzI1ZGJmMSJ9fX0=";

    public AkiRosenthal() {
        super(name, base64);
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
