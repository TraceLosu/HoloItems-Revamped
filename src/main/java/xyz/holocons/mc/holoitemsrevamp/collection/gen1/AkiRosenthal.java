package xyz.holocons.mc.holoitemsrevamp.collection.gen1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class AkiRosenthal extends Idol {

    private static final String name = "akirosenthal";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDQ4NDQ1OSwKICAicHJvZmlsZUlkIiA6ICI5ZDdiYzI4NDlmNjE0OThlYjkwZjkyYmJmOGJjNjg0MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJha2lyb3NlbnRoYWwiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODBhMTQ0ZWMwMzFmMDNiM2YzZGQ0MDcwYzc4YjM0Y2M1YTRiMWI5YmExNWJkNWVhYmY1ZDAyODBkYzI1ZGJmMSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9=";

    public AkiRosenthal() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Aki Rosenthal")
                .color(TextColor.color(0xD80E89))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            