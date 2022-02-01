package xyz.holocons.mc.holoitemsrevamp.collection.gen0;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class Roboko extends Idol {

    private static final String name = "roboko";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDM2Nzg0MSwKICAicHJvZmlsZUlkIiA6ICIxMzJkZmJlMTA3MWQ0OTMyYTVlMGJkYzMyMzUzN2JkNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJyb2JvY29zYW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWIyOWVlYmM5ZWJkZGFiMmZlNmQ4Nzc1YTdiOGE5NGExZDQxMDg5YmI4MTc2Y2E1ZTY2OWU2ZDYxYTgwNjdmNCIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9=";

    public Roboko() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Roboko")
                .color(TextColor.color(0x804F7F))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            