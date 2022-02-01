package xyz.holocons.mc.holoitemsrevamp.collection.gen0;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class TokinoSora extends Idol {

    private static final String name = "tokinosora";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg2ZjZmZWIyODViNTNlN2E4NWY5MjRkYzAzMmQyZTU4MTZmNTA0MmE0NTMwZWVjYzVjMDM0YmVlMTdiMWJkMCJ9fX0=";

    public TokinoSora() {
        super(name, base64);
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
