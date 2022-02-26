package xyz.holocons.mc.holoitemsrevamp.item;

import com.strangeone101.holoitemsapi.CustomItem;
import com.strangeone101.holoitemsapi.HoloItemsAPI;
import com.strangeone101.holoitemsapi.interfaces.Enchantable;
import com.strangeone101.holoitemsapi.recipe.RecipeManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class TideRider extends CustomItem implements Enchantable {

    private final static String name = "tide_rider";
    private final static Material material = Material.TRIDENT;
    private final static String displayName = ChatColor.BLUE + "Tide Rider";
    private final static List<String> lore = List.of(
        "Surf the waves"
    );

    private final NamespacedKey key;

    public TideRider() {
        super(name, material, displayName, lore);
        this.key = new NamespacedKey(HoloItemsAPI.getPlugin(), name);
        this.setStackable(false);
        this.register();
        this.registerRecipe();
    }

    /**
     * Overrides the buildStack method to add riptide enchant.
     * @param player The player to add ownership of the item
     * @return The itemstack
     */

    @Override
    public ItemStack buildStack(Player player) {
        return applyEnchantment(super.buildStack(player));
    }

    private void registerRecipe() {
        final var recipe = new ShapedRecipe(key, buildStack(null));
        recipe.shape(
            "ABC",
            "DEF",
            "GDI"
        );
        recipe.setIngredient('A', Material.PRISMARINE_BRICKS);
        recipe.setIngredient('B', Material.TRIDENT);
        recipe.setIngredient('C', Material.PRISMARINE_BRICKS);
        recipe.setIngredient('E', Material.ENCHANTED_GOLDEN_APPLE);
        recipe.setIngredient('D', Material.PRISMARINE_BRICKS);
        RecipeManager.registerRecipe(recipe);
    }

    @Override
    public @NotNull Enchantment getEnchantment() {
        return Enchantment.getByKey(key);
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
