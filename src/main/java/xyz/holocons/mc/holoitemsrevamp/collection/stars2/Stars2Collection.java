package xyz.holocons.mc.holoitemsrevamp.collection.stars2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class Stars2Collection extends IdolCollection {

    private static final String name = "stars2";

    public Stars2Collection() {
        super(name);
        getIdolSet().add(new AstelLeda());
        getIdolSet().add(new KishidoTemma());
        getIdolSet().add(new YukokuRoberu());
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Holostars Generation 2")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}