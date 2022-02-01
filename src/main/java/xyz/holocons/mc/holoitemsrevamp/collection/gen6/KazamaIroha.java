package xyz.holocons.mc.holoitemsrevamp.collection.gen6;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class KazamaIroha extends Idol {

    private static final String name = "kazamairoha";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWI1ZTA2ZTBhODViNjU3ZjFkZmU1YTU3Y2I3YzliZDdmZDZhYzQzYzYxODJlNWFkYTE5NWU3YzIwYjM3MTY0ZCJ9fX0=";

    public KazamaIroha() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Kazama Iroha")
                .color(TextColor.color(0xA0D9E8))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
