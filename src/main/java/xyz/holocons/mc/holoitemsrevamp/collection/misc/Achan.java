package xyz.holocons.mc.holoitemsrevamp.collection.misc;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class Achan extends Idol {

    private static final String name = "achan";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTgyMzQ3NCwKICAicHJvZmlsZUlkIiA6ICIzMGQ5MGQ1MGQzZjQ0NzZhOTIyMTZmMDM4OGEzYTlkZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJhY2hhbl9VR0EiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTA0OGQ2ZGFlZWI4MDgyNjlhZmY1MzMzNzI0YWY4Y2Y4NTY1NmI5MjM2MGI0NTM1M2FmZjcyZWNhMmIwYzZkNiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";

    public Achan() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("A-Chan")
                .color(TextColor.color(0x534A92))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            