package xyz.holocons.mc.holoitemsrevamp.collection.gen1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class Gen1Collection extends IdolCollection {

    private static final String name = "gen1";

    public Gen1Collection() {
        super(name);
        getIdolSet().add(new AkaiHaato());
        getIdolSet().add(new AkiRosenthal());
        getIdolSet().add(new NatsuiroMatsuri());
        getIdolSet().add(new ShirakamiFubuki());
        getIdolSet().add(new YozoraMel());
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hololive Generation 1")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}

