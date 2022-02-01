package xyz.holocons.mc.holoitemsrevamp.collection.gen5;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class MomosuzuNene extends Idol {

    private static final String name = "momosuzunene";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTM0NjRlODY5ZjZhNmEwNGVhN2M0OTQ4Yjk4YjYyMzM0NWQ2ODViYTMyMzM4YTY5ZGIyOTcxOTJjZTE2NzcyNCJ9fX0=";

    public MomosuzuNene() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Momosuzu Nene")
                .color(TextColor.color(0xFA7B10))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
