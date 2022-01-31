package xyz.holocons.mc.holoitemsrevamp.collection.gen3;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class ShiroganeNoel extends Idol {

    private static final String name = "shiroganenoel";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDg0NTMwMSwKICAicHJvZmlsZUlkIiA6ICJhOThiZTAwOWM2MmQ0M2YxOTFjZWFkNzUzMGNhYjIwNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJzaGlyb2dhbmVub2VsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I2MzI0NjE0NDVlNGJmZjkyMDM5ZGZjYjg5YzViMTdmZGMyZDczYTVkMTEyMGIxYzkyNzFkODY0NGE0ZThjMDgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=";

    public ShiroganeNoel() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Shirogane Noel")
                .color(TextColor.color(0x8A939E))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            