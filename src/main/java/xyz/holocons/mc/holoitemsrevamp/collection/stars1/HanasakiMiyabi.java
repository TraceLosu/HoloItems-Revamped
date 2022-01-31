package xyz.holocons.mc.holoitemsrevamp.collection.stars1;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class HanasakiMiyabi extends Idol {

    private static final String name = "hanasakimiyabi";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyNDg3Mzc5MDYzMCwKICAicHJvZmlsZUlkIiA6ICIxZWExNTY3MjNlMjE0MzM5YmUxNDE1MzBiMTI2M2I5MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaXlhYmlfSGFuYXNha2kiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM2NDQ4NDcyZjRiOWE0OGY4MDM0NThhZWUxMDgwZDliODJmNmFjYzE1OWYzMTJkZWUxZjAwZjM5MGYyYWVhNyIKICAgIH0KICB9Cn0=";

    public HanasakiMiyabi() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Hanasaki Miyabi")
                .color(TextColor.color(0xFF4B5A))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            