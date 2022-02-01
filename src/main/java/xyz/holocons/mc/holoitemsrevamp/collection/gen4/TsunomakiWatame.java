package xyz.holocons.mc.holoitemsrevamp.collection.gen4;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class TsunomakiWatame extends Idol {

    private static final String name = "tsunomakiwatame";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU4M2JhYWZhMjIwMGFhZGJhOWZlYTkxZTBkOGExNzRiYTEwZTNmYzY5NTU3M2M4YTUwZDAwMmYzMzhlNDg2YiJ9fX0=";

    public TsunomakiWatame() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Tsunomaki Watame")
                .color(TextColor.color(0xDBDB89))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
