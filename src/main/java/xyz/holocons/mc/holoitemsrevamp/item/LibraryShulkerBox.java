package xyz.holocons.mc.holoitemsrevamp.item;

import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantManager;
import com.strangeone101.holoitemsapi.enchantment.Enchantable;
import com.strangeone101.holoitemsapi.item.BlockAbility;
import com.strangeone101.holoitemsapi.item.CustomItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.List;

public class LibraryShulkerBox extends CustomItem implements Enchantable, BlockAbility {

    private static final String name = "library_of_memories";
    private static final Material material = Material.SHULKER_BOX;
    private static final Component displayName = Component.text("Library of Memories", NamedTextColor.RED);
    private static final List<Component> lore = List.of(
        Component.text("A shulkerbox to store momentos.", NamedTextColor.DARK_PURPLE)
    );

    private final EnchantManager enchantManager;

    public LibraryShulkerBox(HoloItemsRevamp plugin) {
        super(plugin, name, material, displayName, lore);
        this.enchantManager = plugin.getEnchantManager();
        this.register();
    }

    @Override
    protected Recipe getRecipe() {
        // TODO: Add a recipe.
        //  Maybe the recipe should use a shulker-box in its recipe since this is
        //  a custom-item and NOT an enchantment book.
        return null;
    }

    @Override
    public Enchantment getEnchantment() {
        return CustomEnchantment.getByKey(getKey());
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
