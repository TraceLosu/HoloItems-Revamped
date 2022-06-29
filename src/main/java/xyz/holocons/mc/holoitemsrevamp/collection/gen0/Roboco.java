package xyz.holocons.mc.holoitemsrevamp.collection.gen0;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class Roboco extends Idol {

    private static final String name = "roboco";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWIyOWVlYmM5ZWJkZGFiMmZlNmQ4Nzc1YTdiOGE5NGExZDQxMDg5YmI4MTc2Y2E1ZTY2OWU2ZDYxYTgwNjdmNCJ9fX0=";

    public Roboco(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Roboco")
                .color(TextColor.color(0x804F7F))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
