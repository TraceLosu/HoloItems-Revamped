package xyz.holocons.mc.holoitemsrevamp.collection.gen3;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class UruhaRushia extends Idol {

    private static final String name = "uruharushia";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDg5OTg5NCwKICAicHJvZmlsZUlkIiA6ICJhOGQ0OTY5NjlhOTA0YTNmOGYxNjdmOTNjMTZlYzZlYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ1cnVoYXJ1c2hpYSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMmY3NmI4MWY3ZWFmZGJkNzgyNTJlZjVkYWIyYjRhYTFjYWZkNGFjZjZlMDEzN2EyYTE0Nzk3NWU3YThhZTljIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";

    public UruhaRushia() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Uruha Rushia")
                .color(TextColor.color(0x0CE1BB))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            