package xyz.holocons.mc.holoitemsrevamp.collection.gen3;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class HoushouMarine extends Idol {

    private static final String name = "houshoumarine";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTQ1Mjc5OTMwMTU2OGFiZDVjNWU5YWJjZDU4MzE3NmJiNTkxMGQxNzA4NzE1MTA5YmY2Y2Y1MjM3OWY1N2U3In19fQ==";

    public HoushouMarine() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Houshou Marine")
                .color(TextColor.color(0xA42218))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
