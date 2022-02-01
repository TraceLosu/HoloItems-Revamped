package xyz.holocons.mc.holoitemsrevamp.collection.gamers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class NekomataOkayu extends Idol {

    private static final String name = "nekomataokayu";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDc5ZGQ2ZDYwZWIzOTM0NTY1ODcwOTJhYzQ5MDhmM2U1NmFlYmNiNzVkZTIxODY4MzY1MTJiNjhiOGY2NDUyZCJ9fX0=";

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
