package xyz.holocons.mc.holoitemsrevamp.collection.gen1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class NatsuiroMatsuri extends Idol {

    private static final String name = "natsuiromatsuri";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDU1OTIxNywKICAicHJvZmlsZUlkIiA6ICI1ZDQ4MTRiZDU0ZWM0NDYxOTUyYTM1NTdiOWRiOWE1MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJuYXRzdWlyb21hdHN1cmkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDhlN2UzYjA4YmExN2M4OWRiYzJiODVhYjVmYTViNDk5MTZlZmVmMTQ5YzgwMzViOTZjNjJmMjE1Y2U5YzJjMSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9=";

    public NatsuiroMatsuri() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Natsuiro Matsuri")
                .color(TextColor.color(0xFF5506))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            