package xyz.holocons.mc.holoitemsrevamp.collection.en2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class EN2Collection extends IdolCollection {

    private static final String name = "en1";

    public EN2Collection() {
        super(name);
    }

    @Override
    public @NotNull Material getMaterial() {
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("EN Generation 2")
                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}

