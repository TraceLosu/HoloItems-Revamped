package xyz.holocons.mc.holoitemsrevamp.collection.gen4;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class AmaneKanata extends Idol {

    private static final String name = "amanekanata";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTdhYmRhMGRiYzRhMWE1NDJjNTRhZTM4MDUxMDFhZWUxNWI4MDgyZDVkNjM5ZTI1NDNlODk4OGRjYWZmZTljIn19fQ==";

    public AmaneKanata(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Amane Kanata")
                .color(TextColor.color(0x75C1E7))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
