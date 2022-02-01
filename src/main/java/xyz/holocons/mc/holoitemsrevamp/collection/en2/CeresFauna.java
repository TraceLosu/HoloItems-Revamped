package xyz.holocons.mc.holoitemsrevamp.collection.en2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class CeresFauna extends Idol {

    private static final String name = "ceresfauna";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTBjZDNlMTAyNWM1NGNmNTg4Zjc5NmRjZDBmYTNjNWRkODRjZmZiMTE1OGQ0ZTMxZDljNDlhOWFhMDdmZDAzMCJ9fX0=";

    public CeresFauna() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Ceres Fauna")
                .color(TextColor.color(0x33CA65))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
