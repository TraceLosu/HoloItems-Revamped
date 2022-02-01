package xyz.holocons.mc.holoitemsrevamp.collection.en2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class OuroKronii extends Idol {

    private static final String name = "ourokronii";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTI1ZWFiZjkxNjA4ZGU2ZDI1NGRkOGQwZGU2MjRmZGRiOTkyNDg1NTBkYzZiNDY3NzZlMDlhNmIwNWEyYmFjYiJ9fX0=";

    public OuroKronii() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Ouro Kronii")
                .color(TextColor.color(0x4B4F67))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
