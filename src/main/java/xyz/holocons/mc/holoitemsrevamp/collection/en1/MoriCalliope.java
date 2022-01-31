package xyz.holocons.mc.holoitemsrevamp.collection.en1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class MoriCalliope extends Idol {

    private static final String name = "moricalliope";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDA1NjUwMywKICAicHJvZmlsZUlkIiA6ICIxNGFiZTk5OWFjNjE0MzJhYmEzZjIxNmJiZTk0NTRiMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJtb3JpY2FsbGlvcGVFTiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jNGI3NTk2ZWI5OWUzOGI5MTdkOWI2YjZjOGUyMDEwMDQwOGNlOGE2NmYwMGRkNWNjYTQ4N2ViMmE5MDkwODhjIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";

    public MoriCalliope() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Mori Calliope")
                .color(TextColor.color(0x9F0306))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            