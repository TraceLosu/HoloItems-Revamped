package xyz.holocons.mc.holoitemsrevamp.collection.stars1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class Rikka extends Idol {

    private static final String name = "rikka";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYxNTA4NjU5ODAxOCwKICAicHJvZmlsZUlkIiA6ICIzOTg5OGFiODFmMjU0NmQxOGIyY2ExMTE1MDRkZGU1MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJNeVV1aWRJcyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83MWFiYTdlNmU2MGJjYzgwODJjZDI1MTFmNmQ5NmM4MmRhMzE4NGEwYmIyYjYyZjE3ZDIwMGQ0OGM5NmIzZTczIgogICAgfQogIH0KfQ==";

    public Rikka() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Rikka")
                .color(TextColor.color(0xFED2D5))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            