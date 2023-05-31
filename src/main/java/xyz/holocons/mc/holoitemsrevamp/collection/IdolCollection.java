package xyz.holocons.mc.holoitemsrevamp.collection;

import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

public abstract class IdolCollection {

    private final Set<Idol> idolSet;
    private final ItemStack guiItem;

    public IdolCollection(Idol... idols) {
        this.idolSet = Set.of(idols);
        this.guiItem = buildGuiItem();
    }

    public final Set<Idol> getIdolSet() {
        return idolSet;
    }

    public final ItemStack getGuiItem() {
        return guiItem;
    }

    /**
     * Returns the material that represents the idol collection in the GUI
     * 
     * @return a Bukkit Material
     */
    @NotNull
    public abstract Material getMaterial();

    /**
     * Returns the display name of the itemstack that represents the idol collection in the GUI
     * 
     * @return an Adventure Component
     */
    public abstract Component getDisplayName();

    /**
     * Returns the lore of the itemstack that represents the idol collection in the GUI
     * 
     * @return a list of Adventure Components
     */
    public abstract List<Component> getLore();

    private ItemStack buildGuiItem() {
        var item = new ItemStack(getMaterial());
        var meta = item.getItemMeta();
        meta.displayName(getDisplayName());
        meta.lore(getLore());
        item.setItemMeta(meta);
        return item;
    }
}
