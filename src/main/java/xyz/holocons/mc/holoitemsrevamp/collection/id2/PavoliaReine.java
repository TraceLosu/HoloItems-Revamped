package xyz.holocons.mc.holoitemsrevamp.collection.id2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class PavoliaReine extends Idol {

    private static final String name = "pavoliareine";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTc3NzEzOSwKICAicHJvZmlsZUlkIiA6ICJmOTE4MDRlNTY2Mzc0YWM4OTY4NDY5MjFkOGI3NmI1YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJyZWluZWVlZWUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRjZGQyZjA2N2JiOTY1MjI5N2Q0ZWZlYmViMDlkODEyMThjYzM3NjllMjVlNTg0YTQ4ZDI2NWFhMmM5MzQ4MiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";

    public PavoliaReine() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Pavolia Reine")
                .color(TextColor.color(0x040F7F))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            