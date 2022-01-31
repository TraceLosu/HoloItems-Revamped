package xyz.holocons.mc.holoitemsrevamp.collection.gen6;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class SakamataChloe extends Idol {

    private static final String name = "sakamatachloe";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTQyNDMyNiwKICAicHJvZmlsZUlkIiA6ICI2MTQ4ODI4ZGVkNzk0ODhlYjgyZTU1ODMxYzcwOTcyNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJvbWFwb2wiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ3NmVjMWE0ZGQwNTllOWU5M2FkZWNmYTBkNGRkOThiYWM3MzFiMTFlMjNkMTMyYTdlODg5MTA4MzQ5Y2E1NyIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";

    public SakamataChloe() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Sakamata Chloe")
                .color(TextColor.color(0xCD4E4B))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            