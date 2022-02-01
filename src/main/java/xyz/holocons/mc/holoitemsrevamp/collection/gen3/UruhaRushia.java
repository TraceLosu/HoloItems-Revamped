package xyz.holocons.mc.holoitemsrevamp.collection.gen3;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class UruhaRushia extends Idol {

    private static final String name = "uruharushia";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjJmNzZiODFmN2VhZmRiZDc4MjUyZWY1ZGFiMmI0YWExY2FmZDRhY2Y2ZTAxMzdhMmExNDc5NzVlN2E4YWU5YyJ9fX0=";

    public UruhaRushia() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Uruha Rushia")
                .color(TextColor.color(0x0CE1BB))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
