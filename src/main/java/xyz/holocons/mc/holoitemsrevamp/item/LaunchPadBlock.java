package xyz.holocons.mc.holoitemsrevamp.item;

import com.strangeone101.holoitemsapi.item.BlockAbility;
import com.strangeone101.holoitemsapi.item.CustomItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.List;

public class LaunchPadBlock extends CustomItem implements BlockAbility {

    private static final String name = "launch_pad";
    private static final Material material = Material.SOUL_CAMPFIRE;
    private static final Component displayName = Component.text("Launch Pad", NamedTextColor.WHITE);
    private static final List<Component> lore = List.of(
            Component.text("Light to launch a package", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
    );

    public LaunchPadBlock(HoloItemsRevamp plugin) {
        super(plugin, name, material, displayName, lore);
        register();
    }

    @Override
    protected Recipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(getKey(), buildStack(null));
        recipe.shape("a a","bcb","ded");
        recipe.setIngredient('a', Material.SMOOTH_STONE_SLAB);
        recipe.setIngredient('b', Material.GLASS);
        recipe.setIngredient('c', Material.LODESTONE);
        recipe.setIngredient('d', Material.STONE);
        recipe.setIngredient('e', Material.CAMPFIRE);
        return recipe;
    }

    // TODO: The actual functionality?
}
