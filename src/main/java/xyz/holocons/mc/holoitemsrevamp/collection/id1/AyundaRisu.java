package xyz.holocons.mc.holoitemsrevamp.collection.id1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class AyundaRisu extends Idol {

    private static final String name = "ayundarisu";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTY1NDA2MiwKICAicHJvZmlsZUlkIiA6ICI4YjA2Njg0Zjc2MTU0ODllYTFiNmU4NDgzOGQzOThmMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJBeXVuZGFfUmlzdSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83NjllN2ZlODEyNjkyMzkzNTU0MjQ1YjFmOTNhOTU1MGRkYjRjYzU3NzUyMTFiZjhlMTY3MjI1N2Q5MDZlZThjIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";

    public AyundaRisu() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Ayunda Risu")
                .color(TextColor.color(0xDC7977))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            