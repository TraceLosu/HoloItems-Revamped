package xyz.holocons.mc.holoitemsrevamp.collection.en2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class HakosBaelz extends Idol {

    private static final String name = "hakosbaelz";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyOTk4NzkxOTQxOSwKICAicHJvZmlsZUlkIiA6ICIwNWJhN2FmOGY0M2M0NGFjYWJkZjkzZjVmMTk2Njg3NiIsCiAgInByb2ZpbGVOYW1lIiA6ICJESkdyb3VuZDAiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjRhOTg1ZjE1NjBmNTdjMTgzODNkYjNjYzQ2MTUwMjg3OWY5YjYyOGM0ZDNlMTJhZGUzN2JlNjU2NzMyMzY5MCIKICAgIH0KICB9Cn0=";

    public HakosBaelz() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hakos Baelz")
                .color(TextColor.color(0xD3251E))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            