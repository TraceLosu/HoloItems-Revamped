package xyz.holocons.mc.holoitemsrevamp.collection.gen4;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class TsunowakiWatame extends Idol {

    private static final String name = "tsunowakiwatame";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDkzOTgyNCwKICAicHJvZmlsZUlkIiA6ICJkNTY4YWQ4NTc1Yjg0MjlhYWY1ZTdlMTkwYTZiYWJmZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUc3Vub21ha2l3YXRhbWUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU4M2JhYWZhMjIwMGFhZGJhOWZlYTkxZTBkOGExNzRiYTEwZTNmYzY5NTU3M2M4YTUwZDAwMmYzMzhlNDg2YiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";

    public TsunowakiWatame() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Tsunowaki Watame")
                .color(TextColor.color(0xDBDB89))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            