package xyz.holocons.mc.holoitemsrevamp.collection.stars3;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class KageyamaShien extends Idol {

    private static final String name = "kageyamashien";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYxMTU3OTEzODc0OCwKICAicHJvZmlsZUlkIiA6ICJkODAwZDI4MDlmNTE0ZjkxODk4YTU4MWYzODE0Yzc5OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0aGVCTFJ4eCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMjVhZGNjNTQzYWRhYjNhZjY5Y2Q5MWM5ZjY1ODdlOWM5NjUwMTM0OWMyY2RiMDEyMjMxMDNjNjY0MDUzZjk3IgogICAgfQogIH0KfQ==";

    public KageyamaShien() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Kageyama Shien")
                .color(TextColor.color(0x8B69AE))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            