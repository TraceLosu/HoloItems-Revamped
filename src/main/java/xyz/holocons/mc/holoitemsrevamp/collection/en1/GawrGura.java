package xyz.holocons.mc.holoitemsrevamp.collection.en1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;
import xyz.holocons.mc.holoitemsrevamp.item.TideRider;

import java.util.List;

public class GawrGura extends Idol {

    private static final String name = "gawrgura";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNhNWQ0MzQ5MDZiY2M1ZGRhMDJlODRlMmMwOWVjNzI4NDU5NDAwYjI2NDYzZWM3YmFhOTJiMzVkYmE1MDBkYiJ9fX0=";

    public GawrGura(HoloItemsRevamp plugin) {
        super(name, base64);
        getItemSet().add(new TideRider(plugin));
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Gawr Gura")
                .color(TextColor.color(0x3969B2))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
