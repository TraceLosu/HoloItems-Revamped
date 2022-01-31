package xyz.holocons.mc.holoitemsrevamp.item;

import com.strangeone101.holoitemsapi.CustomItem;
import com.strangeone101.holoitemsapi.Properties;
import com.strangeone101.holoitemsapi.interfaces.Interactable;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.List;

public class TideRider extends CustomItem implements Interactable {

    private final static String name = "tideRider";
    private final static Material material = Material.TRIDENT;
    private final static String displayName = ChatColor.BLUE + "Tide Rider";
    private final static List<String> lore = List.of(
            "Allows you to riptide anywhere you want!"
    );
    private final static long cooldown = 5000;

    public TideRider() {
        super(name, material, displayName, lore);
        this.setMaxDurability(32);
        this.register();
    }

    /**
     * Overrides the buildStack method to add riptide enchant
     * @param player The player to add ownership of the item
     * @return the itemstack
     */
    @Override
    public ItemStack buildStack(Player player) {
        ItemStack item = super.buildStack(player);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.RIPTIDE, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public boolean onInteract(Player player, CustomItem customItem, ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        if(Properties.COOLDOWN.has(dataContainer)){
            if(Properties.COOLDOWN.get(dataContainer) + cooldown > System.currentTimeMillis()){
                return false;
            }
        }
        player.sendBlockChange(player.getLocation().add(0, 0, 0), Material.WATER.createBlockData()); //TODO It creates water, but does not remove it.
        // refresh the chunk, will need a task manager probably.
        Properties.COOLDOWN.set(dataContainer, System.currentTimeMillis());
        itemStack.setItemMeta(meta);
        damageItem(itemStack, 1, player);
        return false;
    }
}
