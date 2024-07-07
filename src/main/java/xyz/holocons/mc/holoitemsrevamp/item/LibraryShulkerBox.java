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
import org.bukkit.block.ShulkerBox;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.BlockStateMeta;
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

    @Override
    public void onBlockDropItem(BlockDropItemEvent event, BlockState blockState) {
        // Note: If there is ever a second shulkerbox-customitem then it will need this too
        // and at that point make a ShulkerBlockAbility interface
        final var items = event.getItems();
        if(items.size() != 1) {
            // Not a shulker box drop, I think?
            BlockAbility.super.onBlockDropItem(event, blockState);
            return;
        }
        final var itemEntity = items.get(0);
        final var oldItemStack = itemEntity.getItemStack();
        if(!isShulkerBox(oldItemStack.getType())) {
            BlockAbility.super.onBlockDropItem(event, blockState);
            return;
        }
        final var oldStackMeta = (BlockStateMeta) oldItemStack.getItemMeta();
        final var oldStackState = (ShulkerBox) oldStackMeta.getBlockState();

        final var newItemStack = buildStack(null);
        newItemStack.editMeta(BlockStateMeta.class, newStackMeta -> {
            final var newStackState = (ShulkerBox) newStackMeta.getBlockState();
            newStackState.getInventory()
                .setContents(oldStackState.getInventory().getContents());
            newStackMeta.setBlockState(newStackState);
        });
        itemEntity.setItemStack(newItemStack);
    }

    private boolean isShulkerBox(Material material) {
        // TODO: Is there a MaterialTag for this? That also handles the colors?
        return material == Material.SHULKER_BOX;
    }
}
