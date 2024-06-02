package xyz.holocons.mc.holoitemsrevamp.item;

import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantManager;
import com.strangeone101.holoitemsapi.enchantment.Enchantable;
import com.strangeone101.holoitemsapi.item.CustomItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.List;

public class PlowBook extends CustomItem implements Enchantable {
    private static final String name = "plow";
    private static final Material material = Material.ENCHANTED_BOOK;
    private static final Component displayName = Component.text("Plow", NamedTextColor.RED);
    private static final List<Component> lore = List.of(
        Component.text("Shovel snow!", NamedTextColor.DARK_PURPLE)
    );

    private final EnchantManager enchantManager;

    public PlowBook(HoloItemsRevamp plugin){
        super(plugin, name, material, displayName, lore);
        this.enchantManager = plugin.getEnchantManager();
        this.register();
    }

    @Override
    protected Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(getKey(), buildStack(null));
        recipe.shape(
            "ABA",
            "ACA",
            "ACA"
        );
        recipe.setIngredient('a', Material.TINTED_GLASS);
        recipe.setIngredient('b', Material.OBSIDIAN);
        recipe.setIngredient('c', Material.STICK);
        return recipe;
    }

    @Override
    public Enchantment getEnchantment() {
        return CustomEnchantment.getByKey(getKey());
    }

    @Override
    public ItemStack applyEnchantment(ItemStack itemStack) {
        // TODO
        return null;
    }
}
