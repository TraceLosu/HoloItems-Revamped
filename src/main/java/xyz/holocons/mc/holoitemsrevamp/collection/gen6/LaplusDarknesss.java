package xyz.holocons.mc.holoitemsrevamp.collection.gen6;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class LaplusDarknesss extends Idol {

    private static final String name = "laplusdarknesss";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGRiMTgwNTYxNDVlZDgwMDIwOWRjZGRhYTFmOTFjNTRkMTkwNDM0M2FjODkwOTg3NDgzYzFkMjRjYjlmZTY5NCJ9fX0=";

    public LaplusDarknesss() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("La+ Darknesss")
                .color(TextColor.color(0x9A6EC7))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
