package xyz.holocons.mc.holoitemsrevamp.item;

import com.strangeone101.holoitemsapi.CustomItem;
import com.strangeone101.holoitemsapi.interfaces.Enchantable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import net.kyori.adventure.text.Component;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.ArrayList;
import java.util.List;

public class TideRider extends CustomItem implements Enchantable {

    private final static String name = "tideRider";
    private final static Material material = Material.TRIDENT;
    private final static String displayName = ChatColor.BLUE + "Tide Rider";
    private final static List<String> lore = List.of(
        "Allows you to riptide anywhere you want!"
    );

    private final HoloItemsRevamp plugin;

    public TideRider(HoloItemsRevamp plugin) {
        super(name, material, displayName, lore);
        this.plugin = plugin;
        this.setMaxDurability(32);
        this.setStackable(false);
        this.register();
    }

    /**
     * Overrides the buildStack method to add riptide enchant.
     * @param player The player to add ownership of the item
     * @return The itemstack
     */
    @Override
    public ItemStack buildStack(Player player) {
        var itemStack = super.buildStack(player);
        var meta = itemStack.getItemMeta();
        meta.addEnchant(Enchantment.RIPTIDE, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public @NotNull Enchantment getEnchantment() {
        return Enchantment.getByKey(new NamespacedKey(plugin, "tide_rider"));
    }

    @Override
    public ItemStack applyEnchantment(ItemStack itemStack) {
        var enchantedStack = itemStack.clone();
        var enchantedMeta = enchantedStack.hasItemMeta() ? enchantedStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(enchantedStack.getType());

        if (enchantedMeta.addEnchant(getEnchantment(), 1, false)) {
            List<Component> lore;
            if (enchantedMeta.hasLore()) {
                lore = enchantedMeta.lore();
            } else {
                lore = new ArrayList<>();
            }
            lore.add(getEnchantment().displayName(1));
            enchantedMeta.lore(lore);
            enchantedStack.setItemMeta(enchantedMeta);
            return enchantedStack;
        } else {
            return null;
        }
    }
}
