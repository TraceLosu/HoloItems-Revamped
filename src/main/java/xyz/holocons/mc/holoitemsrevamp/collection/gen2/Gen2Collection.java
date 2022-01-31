package xyz.holocons.mc.holoitemsrevamp.collection.gen2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.IdolCollection;

import java.util.List;

public class Gen2Collection extends IdolCollection {

    private static final String name = "gen2";

    public Gen2Collection() {
        super(name);
        getIdolSet().add(new MinatoAqua());
        getIdolSet().add(new MurasakiShion());
        getIdolSet().add(new NakiriAyame());
        getIdolSet().add(new OozoraSubaru());
        getIdolSet().add(new YuzukiChoco());
    }

    @Override
    public @NotNull Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hololive Generation 2")

                .color(TextColor.color(0x1D83FF));
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
