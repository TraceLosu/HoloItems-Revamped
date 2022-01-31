package xyz.holocons.mc.holoitemsrevamp.collection.gen2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class MurasakiShion extends Idol {

    private static final String name = "murasakishion";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDcwNDcyMiwKICAicHJvZmlsZUlkIiA6ICIxZmZiYzc2Zjk4YWQ0YzI5OWZjZDhkNzg3OGIyOTI0OCIsCiAgInByb2ZpbGVOYW1lIiA6ICJtdXJhc2FraXNoaW9uIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I5MWQxNzUyYzI1Zjc1Y2EyM2ZhODJhZmE1MjcxMDAxM2IwYTIzZjU3ZjZiOWY2MjI2MmJlNmMyNWI3NzAyOGIiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=";

    public MurasakiShion() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Murasaki Shion")
                .color(TextColor.color(0x8A55CB))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            