package xyz.holocons.mc.holoitemsrevamp.collection.gen2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class MinatoAqua extends Idol {

    private static final String name = "minatoaqua";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDYzMjY5NCwKICAicHJvZmlsZUlkIiA6ICJjYmQ5MzUzNzIxZTk0ODUyOTlmY2ZmNDU5Y2M0Y2U0MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJtaW5hdG9hcXVhIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg1NTNmNTRlZTRhODg2NGI3ZDUyMmQ1Y2E5MjUxNmNlOWU5ZjlhNGFlOGJmM2ZkZDJkMzliZmI3NDM2NGQ4MjgiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=";

    public MinatoAqua() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Minato Aqua")
                .color(TextColor.color(0xFE5DD5))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            