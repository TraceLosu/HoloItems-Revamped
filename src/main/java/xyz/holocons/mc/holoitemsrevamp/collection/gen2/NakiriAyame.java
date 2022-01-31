package xyz.holocons.mc.holoitemsrevamp.collection.gen2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class NakiriAyame extends Idol {

    private static final String name = "nakiriayame";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDY1NTAxMywKICAicHJvZmlsZUlkIiA6ICIyNmMzNDlmOGQyYWM0NWQ5YmUwODAyZDAwYmU3ZmZkNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJuYWtpcmlheWFtZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yYTdjYmIzYWFjZDZlM2QwODI2NDc4YmUzYjZmNDI1OTA5ZDdlMDIzYjMwY2Y3MzMyZjNjYTFmOTk1ZWZhODY4IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";

    public NakiriAyame() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Nakiri Ayame")
                .color(TextColor.color(0xCA2339))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            