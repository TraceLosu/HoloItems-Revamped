package xyz.holocons.mc.holoitemsrevamp.collection.id1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class MoonaHoshinova extends Idol {

    private static final String name = "moonahoshinova";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTYxOTQxMCwKICAicHJvZmlsZUlkIiA6ICIzODAwZjQyMTUxOTU0NzhiYjhmMmMzMjI0Y2M3ZTA0MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNb29uYUhvc2hpbm92YSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jN2NjNzhjM2UyYjlkNzNiYmIxMzc3MWM0ZjVjNWVjMmViMWYwYTMzMjQ4N2NjODFjYjAwMjk2OGMwYTYzNTM5IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";

    public MoonaHoshinova() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Moona Hoshinova")
                .color(TextColor.color(0x7148B3))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            