package xyz.holocons.mc.holoitemsrevamp.collection.id2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class KureijiOllie extends Idol {

    private static final String name = "kureijiollie";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTczODM1NSwKICAicHJvZmlsZUlkIiA6ICIxMmUxMzc2YTY0OTQ0MTMxYWFhM2IzYzM3YmY4OWJhNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJrdXJlaWppb2xsaWUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTljMWQ2NDQ3MGVmZmNlOTU1ZWRlYjIyMzRlODU1Zjg2OWM2OGJkMzdiYTQ1NjJmYmRlNGI5ZjI1ZDE1YWM2ZiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";

    public KureijiOllie() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Kureiji Ollie")
                .color(TextColor.color(0xD50D56))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            