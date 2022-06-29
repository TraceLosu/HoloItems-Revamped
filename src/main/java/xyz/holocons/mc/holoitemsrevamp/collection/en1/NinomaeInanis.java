package xyz.holocons.mc.holoitemsrevamp.collection.en1;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class NinomaeInanis extends Idol {

    private static final String name = "ninomaeinanis";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2IzMDY4YmY3YzQ0MTI4MGRhNTVmMGQ5NjY4NmUzOTQwOGM0NjM1ZWMxMGJmYWY1OTRlOThlNzQzMzE5ZTk2In19fQ==";

    public NinomaeInanis(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Ninomae Inanis")
                .color(TextColor.color(0x3F3F6A))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
