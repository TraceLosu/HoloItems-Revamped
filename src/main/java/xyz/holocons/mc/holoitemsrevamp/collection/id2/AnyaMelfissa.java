package xyz.holocons.mc.holoitemsrevamp.collection.id2;

import java.util.List;

import com.strangeone101.holoitemsapi.CustomItem;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

public class AnyaMelfissa extends Idol {

    private static final String name = "anyamelfissa";
    private static final String base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU0N2YwZWI4ZjM2NDRlOTExMWE5NTdlMTExOTI0NWQ3YTQwMzJkZTIwMDk2ZTAzODg4OWUyZDk2M2FiNzRmNiJ9fX0=";

    public AnyaMelfissa(CustomItem... items) {
        super(name, base64, items);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Anya Melfissa")
                .color(TextColor.color(0xE89D10))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
