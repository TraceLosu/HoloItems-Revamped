package xyz.holocons.mc.holoitemsrevamp.collection;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;

/**
 * A class that holds all idols from a generation.
 * This is mostly used for reiteration on GUI
 */
public abstract class IdolCollection {

    private final String name; //Currently not used, but might be needed to identify an idolCollection from a set.
    private final Set<Idol> idolSet;
    private final ItemStack genItem;

    public IdolCollection(String name, Idol... idols) {
        this.name = name;
        this.idolSet = Set.of(idols);
        this.genItem = initItem();
    }

    /**
     * Initializes an item that represents the generation
     * Used for GUI
     * @return the itemstack
     */
    private ItemStack initItem(){
        ItemStack item = new ItemStack(getMaterial());
        ItemMeta meta = item.getItemMeta();
        meta.displayName(getDisplayName());
        if(getLore() != null){
            List<Component> newLore = getLore();
            meta.lore(newLore);
        }
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Returns an ItemStack that represents the generation
     * @return An itemStack.
     */
    public ItemStack getGenItem() {
        return genItem;
    }

    /**
     * The material that represents the generation
     * Used for GUI
     * @return An item material
     */
    @NotNull
    public abstract Material getMaterial();

    /**
     * Returns the display name of the itemstack that represents the gen
     * Used for GUI
     * @return Adventure Component
     */
    @NotNull
    public abstract Component getDisplayName();

    /**
     * Returns the lore of the itemstack
     * Used for GUI
     * @return List of Adventure Components
     */
    public abstract List<Component> getLore();

    /**
     * Method to get all custom items from a generation
     * @return A set that contains all items from a generation
     */
    public final Set<CustomItem> getAllItem() {
        return idolSet.stream()
                .flatMap((i) -> i.getItemSet().stream()) //Unpacks all nested sets to a single stream
                .collect(Collectors.toSet());
    }

    public final Set<Idol> getIdolSet() {
        return idolSet;
    }
}
