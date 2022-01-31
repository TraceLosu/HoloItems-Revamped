package xyz.holocons.mc.holoitemsrevamp.collection.gen2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.collection.Idol;

import java.util.List;

public class YuzukiChoco extends Idol {

    private static final String name = "yuzukichoco";
    private static final String base64 = "ewogICJ0aW1lc3RhbXAiIDogMTYyMDE4NDY3ODk3MCwKICAicHJvZmlsZUlkIiA6ICIwYzNlMDAzNWQ3Y2U0ZWZhYjNhZGY1YTQzZTJhNzRmNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJ5dXp1a2ljaG9jbyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82MWQ4YjQ2ODhkZTI0YWRkMWNhOTA2ZWQ4YTJiZWE5ZjNhNWNlMjQxNmQyOThlMjViZTA3MmNlMDNiNTBhNjUxIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";

    public YuzukiChoco() {
        super(name, base64);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.text("Yuzuki Choco")
                .color(TextColor.color(0xDA5884))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public List<Component> getLore() {
        return null;
    }
}
            