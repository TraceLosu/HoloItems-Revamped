package xyz.holocons.mc.holoitemsrevamp.collection;

import java.util.List;
import java.util.Set;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;
import xyz.holocons.mc.holoitemsrevamp.Util;

public abstract class Idol {

    private final Set<CustomItem> itemSet;
    private final ItemStack guiItem;

    public Idol(CustomItem... items) {
        this.itemSet = Set.of(items);
        this.guiItem = buildGuiItem();
    }

    public final Set<CustomItem> getItemSet() {
        return itemSet;
    }

    public final ItemStack getGuiItem() {
        return guiItem;
    }

    /**
     * Returns the skin that represents the idol in the GUI
     * 
     * @return a base64-encoded String
     */
    @NotNull
    public abstract String getSkinBase64();

    /**
     * Returns the display name of the itemstack that represents the idol in the GUI
     * 
     * @return an Adventure Component
     */
    public abstract Component getDisplayName();

    /**
     * Returns the lore of the itemstack that represents the idol in the GUI
     * 
     * @return a list of Adventure Components
     */
    public abstract List<Component> getLore();

    private ItemStack buildGuiItem() {
        var item = Util.playerHeadFromBase64(getSkinBase64());
        var meta = item.getItemMeta();
        meta.displayName(getDisplayName());
        meta.lore(getLore());
        item.setItemMeta(meta);
        return item;
    }
}
