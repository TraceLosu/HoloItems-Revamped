package xyz.holocons.mc.holoitemsrevamp.collection.stars3;

import java.util.List;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

public class Stars3Collection extends IdolCollection {

    private static final String name = "stars3";

    public Stars3Collection(Idol... idols) {
        super(name, idols);
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Holostars Generation 3")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
