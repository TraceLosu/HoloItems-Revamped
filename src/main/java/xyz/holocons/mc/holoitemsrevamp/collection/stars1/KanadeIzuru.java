package xyz.holocons.mc.holoitemsrevamp.collection.stars1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class KanadeIzuru extends Idol {

    private static final String name = "kanadeizuru";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyNDg3MzY1NDMxNCwKICAicHJvZmlsZUlkIiA6ICJlNzVkNjkzY2ZjMDM0ODNmOWRhNWEwMmU4Mzg4OWZkNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJrYW5hZGVpenVydSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mMmFjYTM4MGE4M2RhOWNjZmEyNjIxZjNkZWRkYjE5YzRhMWI0MDk1ZjUyMzc1NmFmMTM0YzIzMTY3ZDA1NWJlIgogICAgfQogIH0KfQ==";

    public KanadeIzuru() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Kanade Izuru")
                .color(TextColor.color(0x314BB6))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            