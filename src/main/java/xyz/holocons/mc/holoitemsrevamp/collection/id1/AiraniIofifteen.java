package xyz.holocons.mc.holoitemsrevamp.collection.id1;

import java.util.List;

import com.strangeone101.holoitemsapi.CustomItem;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class AiraniIofifteen extends Idol {

    private static final String name = "airaniiofifteen";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDNiOWQyNzJkNmFmYmUyOGYwNGI3ODljMjc1YzJlY2YwMzMyOTljYTY3MGM1MjI1MTU1YzIwODViMGNlMTUyZSJ9fX0=";

    public AiraniIofifteen(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Airani Iofifteen")
                .color(TextColor.color(0x70D30D))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
