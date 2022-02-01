package xyz.holocons.mc.holoitemsrevamp.collection.stars2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class YukokuRoberu extends Idol {

    private static final String name = "yukokuroberu";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTIyMTlhOTliZmI2MDNlMjUzMjM5NTE5NWQzNzQ2ZGRlZTI1NWYyMTIyNjk5MzZiZDZjYTk3MjQ2MzRmZTg1In19fQ==";

    public YukokuRoberu() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Yukoku Roberu")
                .color(TextColor.color(0xE56B00))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
