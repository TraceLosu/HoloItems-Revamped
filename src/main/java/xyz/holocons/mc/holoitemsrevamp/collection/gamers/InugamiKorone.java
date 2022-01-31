package xyz.holocons.mc.holoitemsrevamp.collection.gamers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class InugamiKorone extends Idol {

    private static final String name = "inugamikorone";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDI0MDUyNCwKICAicHJvZmlsZUlkIiA6ICIxMDc1Mjk2NzVkYWY0N2EyYmUzMTAxZjcwZjBiMzI5NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJpbnVnYW1pa29yb25lIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2UyZTdjZGQ5MGMxMmRhZDlhZmMwMGI5M2E2ZWIwOGZjYWU5MzZjNGQ5ZjkxNWU3NDBkNDVkNzMwMjI5MjU3MzQiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=";

    public InugamiKorone() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Inugami Korone")
                .color(TextColor.color(0xDBB314))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            