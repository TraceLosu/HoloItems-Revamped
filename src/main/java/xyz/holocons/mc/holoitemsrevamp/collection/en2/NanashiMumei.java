package xyz.holocons.mc.holoitemsrevamp.collection.en2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class NanashiMumei extends Idol {

    private static final String name = "nanashimumei";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQ3NmFkMjM1YzAzOTFlYmJkZTcyNTllYjljZDBlYWQ3NzY4MzNkZTI3MjI3N2JlNDc3NTI4ZjBjOGJiMmY3In19fQ==";

    public NanashiMumei() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Nanashi Mumei")
                .color(TextColor.color(0xC29371))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
