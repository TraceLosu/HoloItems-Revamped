package xyz.holocons.mc.holoitemsrevamp.collection.gen4;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class KiryuCoco extends Idol {

    private static final String name = "kiryucoco";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTM3NDk4NywKICAicHJvZmlsZUlkIiA6ICI3YjMzZTBlMjJkNGE0NjEzOTVlZDE5NWU0NWYxN2IwZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJraXJ5dWNvY28iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWZlODczZDI3Y2M1YzM5ODgwYjk0ZGQyZGJmNDViOWM3NTc4OWExOGVhNDQyZmJlZTFmYTI4YWI4N2FjMTk4MSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";

    public KiryuCoco() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Kiryu Coco")
                .color(TextColor.color(0xDC7310))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            