package xyz.holocons.mc.holoitemsrevamp.collection.gamers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class GamersCollection extends IdolCollection {

    private static final String name = "en2";

    public GamersCollection() {
        super(name);
        getIdolSet().add(new InugamiKorone());
        getIdolSet().add(new NekomataOkayu());
        getIdolSet().add(new OokamiMio());
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hololive Gamers")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
