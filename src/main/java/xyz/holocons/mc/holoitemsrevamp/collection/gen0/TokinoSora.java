package xyz.holocons.mc.holoitemsrevamp.collection.gen0;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class TokinoSora extends Idol {

    private static final String name = "tokinosora";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg2ZjZmZWIyODViNTNlN2E4NWY5MjRkYzAzMmQyZTU4MTZmNTA0MmE0NTMwZWVjYzVjMDM0YmVlMTdiMWJkMCJ9fX0=";

    public TokinoSora(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Tokino Sora")
                .color(TextColor.color(0x0444E7))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
