package xyz.holocons.mc.holoitemsrevamp.collection.id2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class AnyaMelfissa extends Idol {

    private static final String name = "anyamelfissa";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYzNjQ0ODM4MDI4OCwKICAicHJvZmlsZUlkIiA6ICJjMGI3Mjk3NzBkZTE0ZmYyODE4MWRlMDRiN2UxZGFkYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJhbnlhYWFhYW0zbGZpIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQ1NDdmMGViOGYzNjQ0ZTkxMTFhOTU3ZTExMTkyNDVkN2E0MDMyZGUyMDA5NmUwMzg4ODllMmQ5NjNhYjc0ZjYiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";

    public AnyaMelfissa() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Anya Melfissa")
                .color(TextColor.color(0xE89D10))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            