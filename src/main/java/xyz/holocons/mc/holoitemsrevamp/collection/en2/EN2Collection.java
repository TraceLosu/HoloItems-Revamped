package xyz.holocons.mc.holoitemsrevamp.collection.en2;

import java.util.List;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

public class EN2Collection extends IdolCollection {

    private static final String name = "en2";

    public EN2Collection(Idol... idols) {
        super(name, idols);
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
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

