package xyz.holocons.mc.holoitemsrevamp.collection.gen3;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class HoushouMarine extends Idol {

    private static final String name = "houshoumarine";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDgxNTU2NCwKICAicHJvZmlsZUlkIiA6ICJkNjA0OTQyZjJiNGI0NDUyYThkYTNkYmY4M2UyMzVjMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJob3VzaG91X21hcmluZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hNDUyNzk5MzAxNTY4YWJkNWM1ZTlhYmNkNTgzMTc2YmI1OTEwZDE3MDg3MTUxMDliZjZjZjUyMzc5ZjU3ZTciLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=";

    public HoushouMarine() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Houshou Marine")
                .color(TextColor.color(0xA42218))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            