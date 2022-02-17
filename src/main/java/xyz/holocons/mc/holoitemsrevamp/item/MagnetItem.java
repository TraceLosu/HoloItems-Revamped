package xyz.holocons.mc.holoitemsrevamp.item;

import com.strangeone101.holoitemsapi.CustomItem;
import com.strangeone101.holoitemsapi.interfaces.Enchantable;
import com.strangeone101.holoitemsapi.recipe.RecipeManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.ArrayList;
import java.util.List;

public class MagnetItem extends CustomItem implements Enchantable {
    // TODO Currently not working as a standalone item because events from Custom items are automatically canceled.
    private final static String name = "magnet";
    private final static Material material = Material.IRON_PICKAXE;
    private final static String displayName = ChatColor.RED + "Magnet";
    private final static List<String> lore = List.of(
        ChatColor.DARK_PURPLE + "Automatically put mined items to your inventory!"
    );

    private final HoloItemsRevamp plugin;

    public MagnetItem(HoloItemsRevamp plugin) {
        super(name, material, displayName, lore);
        this.plugin = plugin;
        this.register();
        this.registerRecipe();
    }

    @Override
    public ItemStack buildStack(Player player) {
        return applyEnchantment(super.buildStack(player));
    }

    private void registerRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "magnet"), buildStack(null));
        recipe.shape(
            "AAB",
            "CDE",
            "FGD"
        );
        recipe.setIngredient('A', Material.POWERED_RAIL);
        recipe.setIngredient('B', Material.IRON_PICKAXE);
        recipe.setIngredient('C', Material.HOPPER);
        recipe.setIngredient('D', Material.IRON_BLOCK);
        recipe.setIngredient('E', Material.REDSTONE);
        recipe.setIngredient('F', Material.DROPPER);
        recipe.setIngredient('G', Material.COMPARATOR);
        RecipeManager.registerRecipe(recipe);
    }

    @Override
    public @NotNull Enchantment getEnchantment() {
        return plugin.getEnchantManager().getCustomEnchantment("magnet");
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
