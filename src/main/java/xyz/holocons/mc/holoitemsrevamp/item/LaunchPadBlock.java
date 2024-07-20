package xyz.holocons.mc.holoitemsrevamp.item;

import com.strangeone101.holoitemsapi.item.BlockAbility;
import com.strangeone101.holoitemsapi.item.CustomItem;
import com.strangeone101.holoitemsapi.tracking.CustomBlockStorage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.Nullable;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.List;

public class LaunchPadBlock extends CustomItem implements BlockAbility {

    private static final String name = "launch_pad";
    private static final Material material = Material.SMOKER;
    private static final Component displayName = Component.text("Launch Pad", NamedTextColor.WHITE);
    private static final List<Component> lore = List.of(
            Component.text("Light to launch a package", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
    );

    private final CustomBlockStorage customBlockTracker;

    public LaunchPadBlock(HoloItemsRevamp plugin) {
        super(plugin, name, material, displayName, lore);
        customBlockTracker = plugin.getTrackingManager();
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

    /**
     * Scans a block to determine the destination location
     * @param block A block (likely package) to scan
     * @return The destination location for this package, or null if a destination could not be determined.
     */
    // TODO: Remove the SuppressWarnings thing after we actually properly get the destination string
    @SuppressWarnings({"ConstantValue", "DataFlowIssue"})
    private @Nullable Block getDestination(Block block) {
        String destinationString = "0 0";
        // TODO: Get the destination string.

        // String format: "x_coord z_coord optional world name potentially with spaces"
        int firstSpace = destinationString.indexOf(' ');
        if(firstSpace == -1) {
            // Either only an x coordinate, or no delimeter between X and Z coordinates
            return null;
        }
        int secondSpace = destinationString.indexOf(' ');

        int destinationX;
        int destinationZ;
        try {
            destinationX = Integer.parseInt(destinationString.substring(0, firstSpace));
            String destinationZString;
            if(secondSpace == -1) {
                destinationZString = destinationString.substring(firstSpace+1);
            }
            else {
                destinationZString = destinationString.substring(firstSpace+1, secondSpace);
            }
            destinationZ = Integer.parseInt(destinationZString);
        } catch (NumberFormatException nfe) {
            // Couldn't get a destination X and Z.
            return null;
        }

        World destinationWorld;
        if(secondSpace == -1) {
            // In this case, there is no world name, so use the default one.
            destinationWorld = block.getWorld();
        }
        else {
            // In this case, there is a world name to use.
            String destinationWorldName = destinationString.substring(secondSpace+1);
            if(destinationWorldName.isBlank()) {
                destinationWorld = block.getWorld();
            }
            else {
                destinationWorld = Bukkit.getWorld(destinationWorldName);
            }
        }

        Block destinationBlock = destinationWorld.getHighestBlockAt(destinationX, destinationZ);

        while(!customBlockTracker.contains(destinationBlock)
            && !(customBlockTracker.getAbility(destinationBlock) instanceof LaunchPadBlock)) {
            if(destinationBlock.getY() == destinationBlock.getWorld().getMinHeight()
            || destinationBlock.getType() == Material.AIR) {
                // Could not find a launch pad at the destination (x,z)
                return null;
            }
            destinationBlock = destinationBlock.getRelative(0, -1, 0);
        }

        return destinationBlock;
    }
}
