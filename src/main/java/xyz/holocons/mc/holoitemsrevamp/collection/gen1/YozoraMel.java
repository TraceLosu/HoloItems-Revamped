package xyz.holocons.mc.holoitemsrevamp.collection.gen1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class YozoraMel extends Idol {

    private static final String name = "yozoramel";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDYwODYwNiwKICAicHJvZmlsZUlkIiA6ICJlY2U2YTcyYjhjZDQ0OTdiYjAxZmFlZDA3MjI5ZmEzZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJ5b3pvcmFtZWwiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWRlNmMxMWU0ODk4NmFlNWM0MTE3N2UyZmIzMTdiMGU3NTdiNTUxM2RjOTVlOGJhMTg5MWQzMjY4YjVhOGZjMCIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9=";

    public YozoraMel() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Yozora Mel")
                .color(TextColor.color(0xFEC104))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            