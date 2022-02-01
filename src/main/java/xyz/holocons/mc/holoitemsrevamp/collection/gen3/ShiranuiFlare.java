package xyz.holocons.mc.holoitemsrevamp.collection.gen3;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class ShiranuiFlare extends Idol {

    private static final String name = "shiranuiflare";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RhN2JhM2YxZjEwNzZkYTQ3OTA0MWU0MDdjNTljNDNmNDcyOGIxZWY0YTMwYzc1NWMyMGJjZmU2YWRkNzczNCJ9fX0=";

    public ShiranuiFlare() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Shiranui Flare")
                .color(TextColor.color(0xFB4E26))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
