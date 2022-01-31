package xyz.holocons.mc.holoitemsrevamp.collection.gen5;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class ShishiroBotan extends Idol {

    private static final String name = "shishirobotan";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NTU0OTA0MywKICAicHJvZmlsZUlkIiA6ICJmYjcyOTk0MzMzNTI0Yzg3YWU4N2JhMTA0YTE3NGQzZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJib3RhYWFuIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzE2MDFjODEyZjAzOTdjODIxMzlhYjZiYWVhNjE0ZTY5ZmQ4YWEzZTU2M2I1NzQzN2E5ZmUwODc5NTc2ZjZiNTEiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";

    public ShishiroBotan() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Shishiro Botan")
                .color(TextColor.color(0x9E8788))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            