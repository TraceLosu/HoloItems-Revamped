package xyz.holocons.mc.holoitemsrevamp.item;

import org.bukkit.Material;

import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public class SaintQuartzItem extends CustomItem {

    private static final String name = "saint_quartz";
    private static final Material material = Material.QUARTZ;
    private static final Component displayName = Component.text("Saint Quartz", NamedTextColor.BLUE);

    public SaintQuartzItem(HoloItemsRevamp plugin) {
        super(plugin, name, material, displayName, null);
        this.register();
    }
}
