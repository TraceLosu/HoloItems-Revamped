package xyz.holocons.mc.holoitemsrevamp.collection.gamers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class NekomataOkayu extends Idol {

    private static final String name = "nekomataokayu";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDI4NDgxMiwKICAicHJvZmlsZUlkIiA6ICJlY2U3NzY5NWIyOTY0MTcyYTdlNGZkMWI5NjUwMzUzNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJuZWtvbWF0YV9va2F5dSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80NzlkZDZkNjBlYjM5MzQ1NjU4NzA5MmFjNDkwOGYzZTU2YWViY2I3NWRlMjE4NjgzNjUxMmI2OGI4ZjY0NTJkIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";

    public NekomataOkayu() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Nekomata Okayu")
                .color(TextColor.color(0xB37DCF))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            