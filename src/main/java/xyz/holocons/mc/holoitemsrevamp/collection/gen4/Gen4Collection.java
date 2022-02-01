package xyz.holocons.mc.holoitemsrevamp.collection.gen4;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class Gen4Collection extends IdolCollection {

    private static final String name = "gen4";

    public Gen4Collection() {
        super(name);
        getIdolSet().add(new AmaneKanata());
        getIdolSet().add(new HimemoriLuna());
        getIdolSet().add(new KiryuCoco());
        getIdolSet().add(new TokoyamiTowa());
        getIdolSet().add(new TsunomakiWatame());
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hololive Generation 4")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
