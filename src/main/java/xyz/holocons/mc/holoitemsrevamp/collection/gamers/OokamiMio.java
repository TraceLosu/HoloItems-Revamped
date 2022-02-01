package xyz.holocons.mc.holoitemsrevamp.collection.gamers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class OokamiMio extends Idol {

    private static final String name = "ookamimio";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2VkYTNhNWFhZjY1YmNhYTIwYzc0NjE4YTA4NjEzMGJmMTA5OTFiODAyZmE5MDMwNTU2MGI3YjAzMTNiNjVhNCJ9fX0=";

    public OokamiMio() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Ookami Mio")
                .color(TextColor.color(0xD91733))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
