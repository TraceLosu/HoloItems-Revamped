package xyz.holocons.mc.holoitemsrevamp.collection.gen0;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class HoshimachiSuisei extends Idol {

    private static final String name = "hoshimachisuisei";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDQ1MjY5NywKICAicHJvZmlsZUlkIiA6ICJhNzcxY2JiNTY1MzM0ZTI3YWIyZjVmZDUxOTM5NDQ4NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJTVUlTRUlfSE9TSU1BVEkiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTRlZWNiM2MwYjRmYzYyNWY1OTQ5OWIwZjJjNDlkNGRiMmQ0MWU0NWFkYmYwZjc5Y2VkM2RiZGViMDc3NjJiMyIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9=";

    public HoshimachiSuisei() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hoshimachi Suisei")
                .color(TextColor.color(0x5E8ABB))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            