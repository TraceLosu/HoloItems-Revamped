package xyz.holocons.mc.holoitemsrevamp.collection.gen2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class NakiriAyame extends Idol {

    private static final String name = "nakiriayame";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmE3Y2JiM2FhY2Q2ZTNkMDgyNjQ3OGJlM2I2ZjQyNTkwOWQ3ZTAyM2IzMGNmNzMzMmYzY2ExZjk5NWVmYTg2OCJ9fX0=";

    public NakiriAyame() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Nakiri Ayame")
                .color(TextColor.color(0xCA2339))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
