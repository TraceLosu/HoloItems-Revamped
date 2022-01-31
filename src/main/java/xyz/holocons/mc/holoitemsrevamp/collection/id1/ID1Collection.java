package xyz.holocons.mc.holoitemsrevamp.collection.id1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class ID1Collection extends IdolCollection {

    private static final String name = "id1";

    public ID1Collection() {
        super(name);
        getIdolSet().add(new AiraniIofifteen());
        getIdolSet().add(new AyundaRisu());
        getIdolSet().add(new MoonaHoshinova());
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hololive ID Generation 1")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
