package xyz.holocons.mc.holoitemsrevamp.collection.gen6;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class HakuiKoyori extends Idol {

    private static final String name = "hakuikoyori";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTkxMDUzZWI1MTA2NjdhYmNhMDE4Mzg0NzFhNmYxZGIzMDBlYzBiNTU1MWUzYmFiNWI3MmFiMDMyNGRiYzI1ZiJ9fX0=";

    public HakuiKoyori() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hakui Koyori")
                .color(TextColor.color(0xFDB4CA))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
