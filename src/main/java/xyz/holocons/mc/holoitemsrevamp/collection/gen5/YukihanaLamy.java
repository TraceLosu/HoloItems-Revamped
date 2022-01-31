package xyz.holocons.mc.holoitemsrevamp.collection.gen5;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class YukihanaLamy extends Idol {

    private static final String name = "yukihanalamy";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTUyMzc1OCwKICAicHJvZmlsZUlkIiA6ICIyM2MxNjNhMWUyYzQ0NzkwYTllZTc5OWU3OWEzY2I1NiIsCiAgInByb2ZpbGVOYW1lIiA6ICJMYW15X1l1a2loYW5hIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzZhMTU5ZjMyMTJlYmUyNjllMGZkN2MwODY2YTI3OTE0YzkxYjVmZDNmNjc2NzJhZmYxNTBmYjAwMzU0MGExMjgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";

    public YukihanaLamy() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Yukihana Lamy")
                .color(TextColor.color(0x3D99CE))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            