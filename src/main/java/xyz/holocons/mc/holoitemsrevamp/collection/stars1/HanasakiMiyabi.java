package xyz.holocons.mc.holoitemsrevamp.collection.stars1;

import java.util.List;

import com.strangeone101.holoitemsapi.CustomItem;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class HanasakiMiyabi extends Idol {

    private static final String name = "hanasakimiyabi";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM2NDQ4NDcyZjRiOWE0OGY4MDM0NThhZWUxMDgwZDliODJmNmFjYzE1OWYzMTJkZWUxZjAwZjM5MGYyYWVhNyJ9fX0=";

    public HanasakiMiyabi(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hanasaki Miyabi")
                .color(TextColor.color(0xFF4B5A))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
