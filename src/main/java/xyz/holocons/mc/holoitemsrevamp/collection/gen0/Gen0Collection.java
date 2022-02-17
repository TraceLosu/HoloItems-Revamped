package xyz.holocons.mc.holoitemsrevamp.collection.gen0;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class Gen0Collection extends IdolCollection {

    private static final String name = "gen0";

    public Gen0Collection(HoloItemsRevamp plugin) {
        super(name);
        getIdolSet().add(new AZKi());
        getIdolSet().add(new HoshimachiSuisei());
        getIdolSet().add(new Roboco(plugin));
        getIdolSet().add(new SakuraMiko());
        getIdolSet().add(new TokinoSora());
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hololive Generation 0")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}

