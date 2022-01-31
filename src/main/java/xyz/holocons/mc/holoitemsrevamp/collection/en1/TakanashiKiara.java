package xyz.holocons.mc.holoitemsrevamp.collection.en1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class TakanashiKiara extends Idol {

    private static final String name = "takanashikiara";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDE0NTUwNiwKICAicHJvZmlsZUlkIiA6ICI2MDRlZDFmZmRlMGI0ZWVmODYyOWRjOTZjYzI5ZDY1MiIsCiAgInByb2ZpbGVOYW1lIiA6ICJLaWFyYV9IT0xPRU4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGRmNzdiZDFlODQ1ZDY5ZDZiZWRjMmZlMGNiZTJhMmJiNTFmYzY4MGQ1MWI5ZWM4ZDMwNjY1MTQyMGMzODMwMCIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9=";

    public TakanashiKiara() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Takanashi Kiara")
                .color(TextColor.color(0xDC3907))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            