package xyz.holocons.mc.holoitemsrevamp.collection.stars3;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class AragamiOga extends Idol {

    private static final String name = "aragamioga";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYzMjk3MzM3MTMzMiwKICAicHJvZmlsZUlkIiA6ICJhYTJhMWE0ZTExZjE0YWViYmE5YmRlZDdiNjIzNjc5MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJhcmFnYW1pb2dhIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZkMzgwNGMxOTUwNDEwMGNiMGViNTIzMWRmYzllY2NmYjFiMDU0ZTY0YTVlNTlkNzE0OTZlNGYyYWMwYjYyMyIKICAgIH0KICB9Cn0=";

    public AragamiOga() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("A4FB41")
                .color(TextColor.color(0xA4FB41))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            