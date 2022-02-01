package xyz.holocons.mc.holoitemsrevamp.collection.stars1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class KanadeIzuru extends Idol {

    private static final String name = "kanadeizuru";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjJhY2EzODBhODNkYTljY2ZhMjYyMWYzZGVkZGIxOWM0YTFiNDA5NWY1MjM3NTZhZjEzNGMyMzE2N2QwNTViZSJ9fX0=";

    public KanadeIzuru() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Kanade Izuru")
                .color(TextColor.color(0x314BB6))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
