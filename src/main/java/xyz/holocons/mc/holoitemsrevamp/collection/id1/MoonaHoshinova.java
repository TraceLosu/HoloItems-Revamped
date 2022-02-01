package xyz.holocons.mc.holoitemsrevamp.collection.id1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class MoonaHoshinova extends Idol {

    private static final String name = "moonahoshinova";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzdjYzc4YzNlMmI5ZDczYmJiMTM3NzFjNGY1YzVlYzJlYjFmMGEzMzI0ODdjYzgxY2IwMDI5NjhjMGE2MzUzOSJ9fX0=";

    public MoonaHoshinova() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Moona Hoshinova")
                .color(TextColor.color(0x7148B3))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
