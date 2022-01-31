package xyz.holocons.mc.holoitemsrevamp.collection.gen5;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class MomosuzuNene extends Idol {

    private static final String name = "momosuzunene";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTQ1NjAxMCwKICAicHJvZmlsZUlkIiA6ICI2MjUyZjE1YjRlOWY0MjA3OWFiOTc5M2RlZTg4NDM2YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJzdXBlcm5lbmVjaGlpIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2EzNDY0ZTg2OWY2YTZhMDRlYTdjNDk0OGI5OGI2MjMzNDVkNjg1YmEzMjMzOGE2OWRiMjk3MTkyY2UxNjc3MjQiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";

    public MomosuzuNene() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Momosuzu Nene")
                .color(TextColor.color(0xFA7B10))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            