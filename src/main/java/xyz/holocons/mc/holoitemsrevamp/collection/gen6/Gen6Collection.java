package xyz.holocons.mc.holoitemsrevamp.collection.gen6;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class Gen6Collection extends IdolCollection {

    private static final String name = "gen6";

    public Gen6Collection() {
        super(name);
        getIdolSet().add(new HakuiKoyori());
        getIdolSet().add(new KazamaIroha());
        getIdolSet().add(new LaplusDarknesss());
        getIdolSet().add(new SakamataChloe());
        getIdolSet().add(new TakaneLui());
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hololive Generation 6")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
