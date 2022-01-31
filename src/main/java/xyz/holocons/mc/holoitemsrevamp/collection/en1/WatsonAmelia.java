package xyz.holocons.mc.holoitemsrevamp.collection.en1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class WatsonAmelia extends Idol {

    private static final String name = "watsonamelia";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDE4ODQ4NywKICAicHJvZmlsZUlkIiA6ICI2YjZjNWZiZDA3ODk0NDg5YjViZjkxOTU0YTI4ZTgyMSIsCiAgInByb2ZpbGVOYW1lIiA6ICJhbXdhdHNvbiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81NDU0NjE5ZmU3MTk4NTZhMzQ0MDZkMWZlY2NjYTQ4MDg1YTVlMDRiMzcxMTI3ZTIwNWI4OTRkZDE5MDk2NGM5IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";

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
            