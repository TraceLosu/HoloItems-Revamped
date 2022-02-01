package xyz.holocons.mc.holoitemsrevamp.collection.gen5;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class ShishiroBotan extends Idol {

    private static final String name = "shishirobotan";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTYwMWM4MTJmMDM5N2M4MjEzOWFiNmJhZWE2MTRlNjlmZDhhYTNlNTYzYjU3NDM3YTlmZTA4Nzk1NzZmNmI1MSJ9fX0=";

    public ShishiroBotan() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Shishiro Botan")
                .color(TextColor.color(0x9E8788))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}