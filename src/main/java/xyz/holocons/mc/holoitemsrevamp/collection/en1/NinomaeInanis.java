package xyz.holocons.mc.holoitemsrevamp.collection.en1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class NinomaeInanis extends Idol {

    private static final String name = "ninomaeinanis";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDEwOTA5MCwKICAicHJvZmlsZUlkIiA6ICI4MjM3ZTljNDFjMTE0YzE5YjI5YTY3YzUxZjIwNDViOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJOaW5vSW5hIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNiMzA2OGJmN2M0NDEyODBkYTU1ZjBkOTY2ODZlMzk0MDhjNDYzNWVjMTBiZmFmNTk0ZTk4ZTc0MzMxOWU5NiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9=";

    public NinomaeInanis() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Ninomae Ina'nis")
                .color(TextColor.color(0x3F3F6A))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            