package xyz.holocons.mc.holoitemsrevamp.collection.gen2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class OozoraSubaru extends Idol {

    private static final String name = "oozorasubaru";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDc0MjYzMiwKICAicHJvZmlsZUlkIiA6ICIxZjM0ZWM5MmJmMGQ0MGI2YjVkYmI4Yjk3MjY1ZDg3ZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJvb3pvcmFzdWJhcnUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGE3MjllNDViN2I4N2U5YzIwYWMyYzI0NTNlYWRmNzQ4MDRlYTA0NzBmYzNjZjA3N2JlMTRmNWI1NTY5Zjk1MiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9=";

    public OozoraSubaru() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Oozora Subaru")
                .color(TextColor.color(0xBCE719))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            