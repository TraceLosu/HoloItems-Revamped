package xyz.holocons.mc.holoitemsrevamp.collection.en1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class WatsonAmelia extends Idol {

    private static final String name = "watsonamelia";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTQ1NDYxOWZlNzE5ODU2YTM0NDA2ZDFmZWNjY2E0ODA4NWE1ZTA0YjM3MTEyN2UyMDViODk0ZGQxOTA5NjRjOSJ9fX0=";

    public WatsonAmelia() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Watson Amelia")
                .color(TextColor.color(0xF2BD37))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
