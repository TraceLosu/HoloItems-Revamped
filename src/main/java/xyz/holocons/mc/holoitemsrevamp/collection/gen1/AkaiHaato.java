package xyz.holocons.mc.holoitemsrevamp.collection.gen1;

import java.util.List;

import com.strangeone101.holoitemsapi.CustomItem;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class AkaiHaato extends Idol {

    private static final String name = "akaihaato";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTc2M2ZjYzY1NzQ5NDBjODNiMTIwY2M2ZGUxNmQ1YzhhZWU0YzRlMzRjMzA0M2E5M2FlZThhN2U3NGFjMWVkNCJ9fX0=";

    public AkaiHaato(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Akai Haato")
                .color(TextColor.color(0xD90629))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
