package xyz.holocons.mc.holoitemsrevamp.collection.gen5;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class Gen5Collection extends IdolCollection {

    private static final String name = "gen5";

    public Gen5Collection() {
        super(name);
        getIdolSet().add(new MomosuzuNene());
        getIdolSet().add(new OmaruPolka());
        getIdolSet().add(new ShishiroBotan());
        getIdolSet().add(new YukihanaLamy());
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
