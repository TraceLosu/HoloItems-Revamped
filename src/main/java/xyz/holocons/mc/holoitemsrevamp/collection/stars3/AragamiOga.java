package xyz.holocons.mc.holoitemsrevamp.collection.stars3;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class AragamiOga extends Idol {

    private static final String name = "aragamioga";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQzODA0YzE5NTA0MTAwY2IwZWI1MjMxZGZjOWVjY2ZiMWIwNTRlNjRhNWU1OWQ3MTQ5NmU0ZjJhYzBiNjIzIn19fQ==";

    public AragamiOga() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Aragami Oga")
                .color(TextColor.color(0xA4FB41))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
