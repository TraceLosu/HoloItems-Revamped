package xyz.holocons.mc.holoitemsrevamp.collection.gen3;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class UsadaPekora extends Idol {

    private static final String name = "usadapekora";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDg3NDMyMCwKICAicHJvZmlsZUlkIiA6ICIzZTI1NTA1MTRlYTg0NjVhYWY0NjI4ZjAxOWVjN2ViYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJVc2FkYXBla29yYSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jOGJiZGIwMDE5NWJmNTY5YWE1ZmRiMGY2MWU1MGJkOTEyMzVlYmIyZjM2YmFhNGFkNjZiNjJmNzRlNTZkZmE1IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";

    public UsadaPekora() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Usada Pekora")
                .color(TextColor.color(0x6BB8E7))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            