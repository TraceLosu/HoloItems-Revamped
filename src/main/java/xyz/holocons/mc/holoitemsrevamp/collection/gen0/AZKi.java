package xyz.holocons.mc.holoitemsrevamp.collection.gen0;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class AZKi extends Idol {

    private static final String name = "azki";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDMxMTEzNSwKICAicHJvZmlsZUlkIiA6ICIwMzFmZDg3ZTgzYTQ0M2FkYWY2MjcxYTNlNGRjNWIzNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBWktpXyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80NWE2NTRmMzgzMDJkMjQ1ZTU5ZWM1ZjlmNmNiNDY3NDhjODM0MmNiNTUyZDc5NjUzZjExOThhNWZhYTBhNDY4IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";

    public AZKi() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("AZKi")
                .color(TextColor.color(0xD11C76))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            