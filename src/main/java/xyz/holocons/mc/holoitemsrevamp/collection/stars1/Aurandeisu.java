package xyz.holocons.mc.holoitemsrevamp.collection.stars1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class Aurandeisu extends Idol {

    private static final String name = "aurandeisu";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyNDg3MzE2Nzk3MSwKICAicHJvZmlsZUlkIiA6ICI2Mzk3ZWE2NTVkNzE0YTZmYmE2OGM0MzE0OTQxODA5OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJBcnVyYW5kZWlzdSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mZTFkNDVjODM5ZDE4YjQ5OGJiYzIxZWExZDBmZGNkYzFjYzA3ZmZiZDgzOTNiMDQyNWYxOTY2YmJiOTYxOGIzIgogICAgfQogIH0KfQ==";

    public Aurandeisu() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Aurandeisu")
                .color(TextColor.color(0x3E815F))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            