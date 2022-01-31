package xyz.holocons.mc.holoitemsrevamp.collection.gen3;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class ShiranuiFlare extends Idol {

    private static final String name = "shiranuiflare";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDc5MzI4NCwKICAicHJvZmlsZUlkIiA6ICI1MDczNzYwYzI4Y2M0OWU0OGJmZTI1NjFhM2Y3ZThlZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJzaGlyYW51aWZsYXJlIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NkYTdiYTNmMWYxMDc2ZGE0NzkwNDFlNDA3YzU5YzQzZjQ3MjhiMWVmNGEzMGM3NTVjMjBiY2ZlNmFkZDc3MzQiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=";

    public ShiranuiFlare() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Shiranui Flare")
                .color(TextColor.color(0xFB4E26))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            