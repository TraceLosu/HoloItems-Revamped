package xyz.holocons.mc.holoitemsrevamp.collection.id2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class ID2Collection extends IdolCollection {

    private static final String name = "id2";

    public ID2Collection() {
        super(name);
        getIdolSet().add(new AnyaMelfissa());
        getIdolSet().add(new KureijiOllie());
        getIdolSet().add(new PavoliaReine());
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hololive ID Generation 2")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}