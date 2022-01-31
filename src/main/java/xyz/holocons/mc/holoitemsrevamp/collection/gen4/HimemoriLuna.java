package xyz.holocons.mc.holoitemsrevamp.collection.gen4;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class HimemoriLuna extends Idol {

    private static final String name = "himemoriluna";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTk5MzYwNiwKICAicHJvZmlsZUlkIiA6ICI3NjI0ZjA3YzU0NTM0YzZjODhjMWY4NTMxNmJkOWUwMSIsCiAgInByb2ZpbGVOYW1lIiA6ICJIaW1lbW9yaUx1bmEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJkZTI2ODMxZjViNDQ2MTM1N2MyZWI4OWI2ODQ1ZDM0NzRiMjUyMjgyN2Y0MDY2ZGZlYThlZGMxYTgyNGY3MSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";

    public HimemoriLuna() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Himemori Luna")
                .color(TextColor.color(0xD86EAD))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            