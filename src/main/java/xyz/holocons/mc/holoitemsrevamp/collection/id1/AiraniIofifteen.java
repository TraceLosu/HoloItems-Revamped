package xyz.holocons.mc.holoitemsrevamp.collection.id1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class AiraniIofifteen extends Idol {

    private static final String name = "airaniiofifteen";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTU4MjMyNCwKICAicHJvZmlsZUlkIiA6ICIwODA0NjE2OTZmNzc0NWY3ODM3NTFlMTFmNDA0ODhmNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJJT0ZJMTUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDNiOWQyNzJkNmFmYmUyOGYwNGI3ODljMjc1YzJlY2YwMzMyOTljYTY3MGM1MjI1MTU1YzIwODViMGNlMTUyZSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";

    public AiraniIofifteen() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Airani Iofifteen")
                .color(TextColor.color(0x70D30D))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            