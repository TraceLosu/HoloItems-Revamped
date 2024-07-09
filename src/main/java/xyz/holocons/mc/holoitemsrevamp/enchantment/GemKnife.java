package xyz.holocons.mc.holoitemsrevamp.enchantment;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.util.Consumer;
import org.jetbrains.annotations.NotNull;

import com.destroystokyo.paper.MaterialTags;
import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantmentAbility;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public class GemKnife extends CustomEnchantment implements EnchantmentAbility {

    /**
     * When you GemKnife a Block, what item will get spit out/output?
     */
    private static final Map<Material, Material> COMPATIBLE_MATERIALS = Map.ofEntries(
            Map.entry(Material.COAL_ORE, Material.COAL_BLOCK),
            Map.entry(Material.DEEPSLATE_COAL_ORE, Material.COAL_BLOCK),
            Map.entry(Material.IRON_ORE, Material.IRON_BLOCK),
            Map.entry(Material.DEEPSLATE_IRON_ORE, Material.IRON_BLOCK),
            Map.entry(Material.GOLD_ORE, Material.GOLD_BLOCK),
            Map.entry(Material.DEEPSLATE_GOLD_ORE, Material.GOLD_BLOCK),
            Map.entry(Material.NETHER_GOLD_ORE, Material.GOLD_BLOCK),
            Map.entry(Material.GILDED_BLACKSTONE, Material.GOLD_BLOCK),
            Map.entry(Material.REDSTONE_ORE, Material.REDSTONE_BLOCK),
            Map.entry(Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE_BLOCK),
            Map.entry(Material.LAPIS_ORE, Material.LAPIS_BLOCK),
            Map.entry(Material.DEEPSLATE_LAPIS_ORE, Material.LAPIS_BLOCK),
            Map.entry(Material.NETHER_QUARTZ_ORE, Material.QUARTZ_BLOCK),
            Map.entry(Material.GLOWSTONE, Material.GLOWSTONE));

    /**
     * How much of each material do you get per-netherite-ingot?
     */
    private static final Map<Material, Integer> MATERIALS_PER_INGOT = Map.ofEntries(
        Map.entry(Material.COAL_BLOCK, 64),
        Map.entry(Material.IRON_BLOCK, 64),
        Map.entry(Material.GOLD_BLOCK, 64),
        Map.entry(Material.REDSTONE_BLOCK, 64),
        Map.entry(Material.LAPIS_BLOCK, 64),
        Map.entry(Material.QUARTZ_BLOCK, 64),
        Map.entry(Material.GLOWSTONE, 64)
    );

    public GemKnife(HoloItemsRevamp plugin) {
        super(plugin, "gem_knife");
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return item.getType() == Material.EMERALD;
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text("Gem Knife", NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public int getCostMultiplier() {
        // TODO: should this be infinity instead?
        return 12;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event, ItemStack itemStack) {
        final var clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) {
            return;
        }
        final var materialToDrop = COMPATIBLE_MATERIALS.get(clickedBlock.getType());
        if (materialToDrop == null) {
            return;
        }
        final var amountPerIngot = MATERIALS_PER_INGOT.get(materialToDrop);
        if(amountPerIngot == null) {
            // Shouldn't be possible, but just in case.
            return;
        }

        final var ingotsTaken = removeFromInventory(
            event.getPlayer().getInventory(), 1, Material.NETHERITE_INGOT);
        final var amountToDrop = amountPerIngot * ingotsTaken;

        clickedBlock.getWorld().dropItemNaturally(clickedBlock.getLocation(),
            new ItemStack(materialToDrop, amountToDrop));
    }

    /**
     * Removes an item from an inventory. Will recursively check into shulker boxes.
     * @param inventory The inventory to scan for an item
     * @param maxToTake The maximum amount of the item to remove from the inventory
     * @param materialToTake What kind of material should be searched for/taken
     * @return How many of the item was successfully removed from the inventory
     */
    private static int removeFromInventory(Inventory inventory, final int maxToTake, Material materialToTake) {
        int amountTaken = 0;
        for(ItemStack stack : inventory.getContents()) {
            if(stack == null || stack.isEmpty()) {
                continue;
            }

            // TODO: Scan shulkerboxes
            
            if(stack.getType() != materialToTake) {
                continue;
            }

            final var stackSize = stack.getAmount();
            if(stackSize + amountTaken >= maxToTake) {
                // This stack has enough items.
                final var amountToRemove = maxToTake - amountTaken;
                stack.setAmount(stackSize - amountToRemove);
                return maxToTake;
            }
            else {
                // This stack does not have enough items; absorb the entire stack.
                amountTaken += stackSize;
                stack.setType(Material.AIR);
            }
        }
        return amountTaken;
    }

    /**
     * Edit the inventory of a shulkerbox. After the consumer is called,
     * the shulker's inventory is saved, including any edits made.
     *
     * @param itemStack The (possible) shulkerbox to edit
     * @param action    The action to perform on the shulker's inventory.
     */
    private static void editShulkerBoxInventory(ItemStack itemStack, Consumer<Inventory> action) {
        itemStack.editMeta(BlockStateMeta.class, blockStateMeta -> {
            if (blockStateMeta.getBlockState() instanceof ShulkerBox shulkerBox) {
                action.accept(shulkerBox.getInventory());
            }
        });
    }

    private static Inventory getShulkerBoxInventory(final ItemStack itemStack) {
        if (itemStack.getItemMeta() instanceof BlockStateMeta blockStateMeta
                && blockStateMeta.getBlockState() instanceof ShulkerBox shulkerBox) {
            return shulkerBox.getInventory();
        } else {
            return null;
        }
    }
}
