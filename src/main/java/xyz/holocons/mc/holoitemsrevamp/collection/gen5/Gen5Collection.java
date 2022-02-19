package xyz.holocons.mc.holoitemsrevamp.collection.gen5;

import java.util.List;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

public class Gen5Collection extends IdolCollection {

    private static final String name = "gen5";

    public Gen5Collection(Idol... idols) {
        super(name, idols);
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hololive Generation 5")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
