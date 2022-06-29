package xyz.holocons.mc.holoitemsrevamp.collection.gen1;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class NatsuiroMatsuri extends Idol {

    private static final String name = "natsuiromatsuri";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDhlN2UzYjA4YmExN2M4OWRiYzJiODVhYjVmYTViNDk5MTZlZmVmMTQ5YzgwMzViOTZjNjJmMjE1Y2U5YzJjMSJ9fX0=";

    public NatsuiroMatsuri(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Natsuiro Matsuri")
                .color(TextColor.color(0xFF5506))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
