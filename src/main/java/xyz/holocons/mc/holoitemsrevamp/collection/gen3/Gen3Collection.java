package xyz.holocons.mc.holoitemsrevamp.collection.gen3;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class Gen3Collection extends IdolCollection {

    private static final String name = "gen3";

    public Gen3Collection() {
        super(name);
        getIdolSet().add(new HoushouMarine());
        getIdolSet().add(new ShiranuiFlare());
        getIdolSet().add(new ShiroganeNoel());
        getIdolSet().add(new UruhaRushia());
        getIdolSet().add(new UsadaPekora());
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hololive Generation 3")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}

