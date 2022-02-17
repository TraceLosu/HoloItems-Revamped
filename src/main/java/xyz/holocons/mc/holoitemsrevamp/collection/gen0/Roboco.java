package xyz.holocons.mc.holoitemsrevamp.collection.gen0;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;
import xyz.holocons.mc.holoitemsrevamp.item.MagnetItem;

import java.util.List;

public class Roboco extends Idol {

    private static final String name = "roboco";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWIyOWVlYmM5ZWJkZGFiMmZlNmQ4Nzc1YTdiOGE5NGExZDQxMDg5YmI4MTc2Y2E1ZTY2OWU2ZDYxYTgwNjdmNCJ9fX0=";

    public Roboco(HoloItemsRevamp plugin) {
        super(name, base64);
        getItemSet().add(new MagnetItem(plugin));
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
