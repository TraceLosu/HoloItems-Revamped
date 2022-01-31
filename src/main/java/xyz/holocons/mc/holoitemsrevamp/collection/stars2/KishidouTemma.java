package xyz.holocons.mc.holoitemsrevamp.collection.stars2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class KishidouTemma extends Idol {

    private static final String name = "kishidoutemma";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyNDg3Mzg5NDk4OCwKICAicHJvZmlsZUlkIiA6ICJjNzJjNjU1YzU5NDA0ZjVmYjUzOTMwZWRkZDFjOTNkMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJraXNoaWRvdGVtbWEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmJhNTI1MjhjMTM4M2QwMzI5MzJjNzQxYTQ4ZWQyYTg3Y2JiMjJjOTllMWIzOTdkNTMyMjQ1NTE1NTU4M2MwYSIKICAgIH0KICB9Cn0=";

    public KishidouTemma() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Kishidou Temma")
                .color(TextColor.color(0xF5ED94))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            