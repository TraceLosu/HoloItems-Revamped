package xyz.holocons.mc.holoitemsrevamp.collection.id1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class AyundaRisu extends Idol {

    private static final String name = "ayundarisu";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzY5ZTdmZTgxMjY5MjM5MzU1NDI0NWIxZjkzYTk1NTBkZGI0Y2M1Nzc1MjExYmY4ZTE2NzIyNTdkOTA2ZWU4YyJ9fX0=";

    public AyundaRisu() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Ayunda Risu")
                .color(TextColor.color(0xDC7977))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
