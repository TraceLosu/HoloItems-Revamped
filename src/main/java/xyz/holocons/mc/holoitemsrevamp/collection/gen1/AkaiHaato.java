package xyz.holocons.mc.holoitemsrevamp.collection.gen1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class AkaiHaato extends Idol {

    private static final String name = "akaihaato";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDUzMzY1OSwKICAicHJvZmlsZUlkIiA6ICJmMDNhNWE4ODZlY2M0OTMwYWUyMTA4MWI5MjYzNDhmZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBa2FpSGFhdG8iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTc2M2ZjYzY1NzQ5NDBjODNiMTIwY2M2ZGUxNmQ1YzhhZWU0YzRlMzRjMzA0M2E5M2FlZThhN2U3NGFjMWVkNCIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9=";

    public AkaiHaato() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Akai Haato")
                .color(TextColor.color(0xD90629))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            