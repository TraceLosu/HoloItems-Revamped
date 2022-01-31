package xyz.holocons.mc.holoitemsrevamp.collection.stars1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class Stars1Collection extends IdolCollection {

    private static final String name = "stars1";

    public Stars1Collection() {
        super(name);
        getIdolSet().add(new Aurandeisu());
        getIdolSet().add(new HanasakiMiyabi());
        getIdolSet().add(new KanadeIzuru());
        getIdolSet().add(new Rikka());
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Holostars Generation 1")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}