package xyz.holocons.mc.holoitemsrevamp.collection.id2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class KureijiOllie extends Idol {

    private static final String name = "kureijiollie";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTljMWQ2NDQ3MGVmZmNlOTU1ZWRlYjIyMzRlODU1Zjg2OWM2OGJkMzdiYTQ1NjJmYmRlNGI5ZjI1ZDE1YWM2ZiJ9fX0=";

    public KureijiOllie() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Kureiji Ollie")
                .color(TextColor.color(0xD50D56))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
