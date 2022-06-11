package xyz.holocons.mc.holoitemsrevamp.item;

import com.strangeone101.holoitemsapi.CustomItem;
import com.strangeone101.holoitemsapi.enchantments.EnchantManager;
import com.strangeone101.holoitemsapi.enchantments.Enchantable;
import com.strangeone101.holoitemsapi.recipe.RecipeManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.List;

public class MagnetBook extends CustomItem implements Enchantable {

    // TODO: The magnet enchantment should be given on an enchanted book
    private final static String name = "magnet";
    private final static Material material = Material.IRON_PICKAXE;
    private final static Component displayName = Component.text("Magnet", NamedTextColor.RED);
    private final static List<Component> lore = List.of(
        Component.text("Automatically put mined items to your inventory!", NamedTextColor.DARK_PURPLE)
    );

    private final EnchantManager enchantManager;

    public MagnetBook(HoloItemsRevamp plugin) {
        super(name, material, displayName, lore, plugin);
        this.enchantManager = plugin.getEnchantManager();
        this.register();
        this.registerRecipe();
    }

    @Override
    public ItemStack buildStack(Player player) {
        return applyEnchantment(super.buildStack(player));
    }

    private void registerRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(getKey(), buildStack(null));
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
    public Enchantment getEnchantment() {
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
