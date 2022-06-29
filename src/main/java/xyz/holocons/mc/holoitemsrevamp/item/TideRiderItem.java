package xyz.holocons.mc.holoitemsrevamp.item;

import com.strangeone101.holoitemsapi.enchantment.EnchantManager;
import com.strangeone101.holoitemsapi.enchantment.Enchantable;
import com.strangeone101.holoitemsapi.item.CustomItem;
import com.strangeone101.holoitemsapi.recipe.RecipeManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.List;

public class TideRiderItem extends CustomItem implements Enchantable {

    private final static String name = "tide_rider";
    private final static Material material = Material.TRIDENT;
    private final static Component displayName = Component.text("Tide Rider", NamedTextColor.BLUE);
    private final static List<Component> lore = List.of(
        Component.text("Surf the waves")
    );

    private final EnchantManager enchantManager;

    public TideRiderItem(HoloItemsRevamp plugin) {
        super(name, material, displayName, lore, plugin);
        this.enchantManager = plugin.getEnchantManager();
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
        final var recipe = new ShapedRecipe(getKey(), buildStack(null));
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
        return Enchantment.getByKey(getKey());
    }

    @Override
    public ItemStack applyEnchantment(ItemStack itemStack) {
        var enchantedStack = itemStack.clone();
        var enchantedMeta = enchantedStack.hasItemMeta() ? enchantedStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(enchantedStack.getType());

        if (enchantedMeta.addEnchant(getEnchantment(), 1, false)) {
            enchantedStack.setItemMeta(enchantedMeta);
            enchantManager.removeCustomEnchantmentLore(enchantedStack);
            enchantManager.applyCustomEnchantmentLore(enchantedStack);
            return enchantedStack;
        } else {
            return null;
        }
    }
}
