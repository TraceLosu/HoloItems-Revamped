package xyz.holocons.mc.holoitemsrevamp.collection.gen0;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class SakuraMiko extends Idol {

    private static final String name = "sakuramiko";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk5ZGMwMWRjZjI4NDQ1NTc2ZjIyNjg4ODJhNzc3MDZmY2I5MzUzYWIwYzk1NGY5NjA0NTU2MWE3OTI0NGMxZSJ9fX0=";

    public SakuraMiko() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Sakura Miko")
                .color(TextColor.color(0xFF4C74))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
