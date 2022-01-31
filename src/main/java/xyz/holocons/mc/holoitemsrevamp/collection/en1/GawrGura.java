package xyz.holocons.mc.holoitemsrevamp.collection.en1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;
import xyz.holocons.mc.holoitemsrevamp.item.TideRider;

import java.util.List;

public class GawrGura extends Idol {

    private static final String name = "gawrgura";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE2NzY5ODkxMiwKICAicHJvZmlsZUlkIiA6ICI0YjA1ZDhkMzIzNjI0M2MyOTQ5NGIyODY2YzcwMTI2NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJHYXVHdXJhIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzMzYTVkNDM0OTA2YmNjNWRkYTAyZTg0ZTJjMDllYzcyODQ1OTQwMGIyNjQ2M2VjN2JhYTkyYjM1ZGJhNTAwZGIiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=";

    public GawrGura() {
        super(name, base64);
        getItemSet().add(new TideRider());
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Gawr Gura")
                .color(TextColor.color(0x3969B2))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            