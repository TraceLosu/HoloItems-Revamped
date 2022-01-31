package xyz.holocons.mc.holoitemsrevamp.collection.gen0;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class SakuraMiko extends Idol {

    private static final String name = "sakuramiko";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDMzNDgzMiwKICAicHJvZmlsZUlkIiA6ICI4NDhmNTdmMDE5NjY0NzhlYmFjMDkzNWJkZGFlMDRmOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJzYWt1cmFtaWtvMzUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk5ZGMwMWRjZjI4NDQ1NTc2ZjIyNjg4ODJhNzc3MDZmY2I5MzUzYWIwYzk1NGY5NjA0NTU2MWE3OTI0NGMxZSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9=";

    public SakuraMiko() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Sakura Miko")
                .color(TextColor.color(0xFF4C74))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            