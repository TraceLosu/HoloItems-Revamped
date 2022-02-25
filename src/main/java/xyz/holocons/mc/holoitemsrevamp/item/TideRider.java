package xyz.holocons.mc.holoitemsrevamp.item;

import com.strangeone101.holoitemsapi.CustomItem;
import com.strangeone101.holoitemsapi.HoloItemsAPI;
import com.strangeone101.holoitemsapi.interfaces.Enchantable;
import com.strangeone101.holoitemsapi.recipe.RecipeManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import com.strangeone101.holoitemsapi.interfaces.Interactable;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
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
        return applyEnchantment(super.buildStack(player));
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

    private void registerRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(key, buildStack(null));
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

    /*
    @Override
    public boolean onInteract(Player player, CustomItem customItem, ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        BukkitRunnable tideriderlogic = new BukkitRunnable() {
            final World world = player.getWorld();
            int increment = 0;


            @Override
            public void run() {
                Location location = player.getLocation();
                Vector direction = location.getDirection().setY(0.0001).normalize();
                Block front = world.getBlockAt(location.clone().add(direction));
                Block below = world.getBlockAt(location.add(0, -1, 0));

                if (!player.isValid() || !player.isHandRaised() || increment>=1200){
                    meta.removeEnchant(Enchantment.RIPTIDE);
                    meta.addEnchant(Enchantment.LOYALTY, 3, false);
                    itemStack.setItemMeta(meta);
                    cancel();
                }
                else if(increment==0){
                    meta.removeEnchant(Enchantment.LOYALTY);
                    meta.addEnchant(Enchantment.RIPTIDE, 3, false);
                    itemStack.setItemMeta(meta);
                }
                increment ++;

                double multiplier = 1;
                if (player.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE))
                    multiplier += 0.33;
                if (below.getType() == Material.SOUL_SAND)
                    multiplier -= 0.33;
                ItemStack boots = player.getInventory().getBoots();
                if (boots == null || boots.getType() == Material.AIR)
                    ;
                else {
                    multiplier += 0.11 * boots.getEnchantmentLevel(Enchantment.DEPTH_STRIDER);
                    if (boots.getEnchantments().containsKey(Enchantment.SOUL_SPEED) && below.getType() == Material.SOUL_SAND)
                        multiplier += 0.33 * boots.getEnchantmentLevel(Enchantment.SOUL_SPEED);
                }


                double yvelocity;
                if (front.isPassable() && !front.isLiquid())
                    if (below.isPassable() && !below.isLiquid())
                        yvelocity = -0.5;

                    else
                        yvelocity = 0;

                else
                    if (!below.isPassable() && !below.isLiquid())
                        yvelocity = 0.5;

                    else
                        yvelocity = 0;

                player.setVelocity(player.getVelocity().add(direction).normalize().multiply(multiplier)
                    .setY(yvelocity));


                player.setFallDistance(0);
            }

        };
        tideriderlogic.runTaskTimer(HoloItemsRevamp.getInstance(), 0, 1);
        return false;
    }
     */
}
