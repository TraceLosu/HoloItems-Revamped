package xyz.holocons.mc.holoitemsrevamp.collection.gen4;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class TokoyamiTowa extends Idol {

    private static final String name = "tokoyamitowa";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTAwMzM1OSwKICAicHJvZmlsZUlkIiA6ICI4ZDI1N2QwMDU3Y2Q0YmI1YTc1YTRlZTQ3NmY3Mjk1MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJUb3dhc2FtYSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kZjk1ZmMyMzM5MmFmN2U1YzVjMjBjNzZhMjIwOGRmNDkyMmIxMDhmMmM4OWUzMzUyMjQwMjYxMmM1OGRiNTA4IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";

    public TokoyamiTowa() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Tokoyami Towa")
                .color(TextColor.color(0x805A9E))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            