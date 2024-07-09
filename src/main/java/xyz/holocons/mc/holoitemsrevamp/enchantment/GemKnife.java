package xyz.holocons.mc.holoitemsrevamp.enchantment;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
            Map.entry(Material.COAL_ORE, Material.COAL),
            Map.entry(Material.DEEPSLATE_COAL_ORE, Material.COAL),
            Map.entry(Material.IRON_ORE, Material.IRON_NUGGET),
            Map.entry(Material.DEEPSLATE_IRON_ORE, Material.IRON_NUGGET),
            Map.entry(Material.GOLD_ORE, Material.GOLD_NUGGET),
            Map.entry(Material.DEEPSLATE_GOLD_ORE, Material.GOLD_NUGGET),
            Map.entry(Material.NETHER_GOLD_ORE, Material.GOLD_NUGGET),
            Map.entry(Material.GILDED_BLACKSTONE, Material.GOLD_NUGGET),
            Map.entry(Material.REDSTONE_ORE, Material.REDSTONE),
            Map.entry(Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE),
            Map.entry(Material.LAPIS_ORE, Material.LAPIS_LAZULI),
            Map.entry(Material.DEEPSLATE_LAPIS_ORE, Material.LAPIS_LAZULI),
            Map.entry(Material.NETHER_QUARTZ_ORE, Material.QUARTZ),
            Map.entry(Material.GLOWSTONE, Material.GLOWSTONE_DUST));

    /**
     * When get a certain item from a GemKnife, how much charge should be needed per
     * item output?
     */
    private static final Map<Material, Integer> CHARGE_PER_ITEM = Map.ofEntries(
            Map.entry(Material.COAL, 6), // 1 emerald per 1 coal
            Map.entry(Material.IRON_NUGGET, 2), // 1 emerald per 3 iron nuggets
            Map.entry(Material.GOLD_NUGGET, 3), // 1 emerald per 2 gold nuggets
            Map.entry(Material.REDSTONE, 6), // 1 emerald per 1 redstone
            Map.entry(Material.LAPIS_LAZULI, 18), // 3 emerald per 1 lapis
            Map.entry(Material.QUARTZ, 12), // 2 emerald per 1 quartz
            Map.entry(Material.GLOWSTONE_DUST, 6) // 1 emerald per 1 glowstone dust
    );

    private static final int CHARGE_PER_EMERALD = 6;
    private static final int MAX_DROPS_PER_INTERACT = 64;

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
        final var chargePerDrop = CHARGE_PER_ITEM.get(materialToDrop);
        if (chargePerDrop == null) {
            // Should be impossible, but doing this hints to my compiler that it's not-null.
            return;
        }

        final var maxChargeToGet = chargePerDrop * MAX_DROPS_PER_INTERACT;
        final var chargeGrabbed = getChargeFromInventory(
                event.getPlayer().getInventory(), maxChargeToGet, true);
        final var amountToDrop = chargeGrabbed / chargePerDrop;
        final var remainingCharge = chargeGrabbed - (amountToDrop * chargePerDrop);
        // There might be excess emeralds if you try to grab lapis (3 emeralds per item)
        // but you have 4 emeralds in inventory (1 excess)
        final var excessEmeralds = remainingCharge / CHARGE_PER_EMERALD;

        final var location = clickedBlock.getLocation();
        location.getWorld().dropItemNaturally(location, new ItemStack(materialToDrop, amountToDrop));
        location.getWorld().dropItemNaturally(location, new ItemStack(Material.EMERALD, excessEmeralds));
    }

    private static int getChargeFromInventory(Inventory inv, final int maxChargeToGet, boolean checkShulkers) {
        AtomicInteger chargeGotten = new AtomicInteger(0);
        for (final var itemStack : inv.getContents()) {
            if (itemStack == null) {
                continue;
            }

            final int remainingChargeToGet = maxChargeToGet - chargeGotten.get();
            if (remainingChargeToGet == 0) {
                continue;
            }

            int chargeFromThisStack;
            if (checkShulkers && MaterialTags.SHULKER_BOXES.isTagged(itemStack)) {
                // TODO: Test this.
                AtomicInteger atomicCharge = new AtomicInteger();
                editShulkerBoxInventory(itemStack, shulkerInv -> atomicCharge
                        .set(getChargeFromInventory(shulkerInv, remainingChargeToGet, false)));
                chargeFromThisStack = atomicCharge.get();
            } else {
                chargeFromThisStack = getChargeFromStack(itemStack, remainingChargeToGet);
            }
            chargeGotten.addAndGet(chargeFromThisStack);
        }
        return chargeGotten.get();
    }

    /**
     * Tries to get charge from this itemStack. Edits the itemStack accordingly, and
     * will never take more charge
     * than maxCharge.
     * 
     * @return The charge gained from this stack. Guaranteed to be 0 <= return <=
     *         maxCharge.
     */
    private static int getChargeFromStack(ItemStack stack, int maxCharge) {
        if (!stack.getEnchantments().isEmpty()) {
            // Even if it's an emerald or emerald block it could still be:
            // - A gemknife (don't absorb)
            // - An FF item (don't absorb)
            // - Maybe an ARH item? (don't absorb)
            return 0;
        }

        int chargePerItem;
        if (stack.getType() == Material.EMERALD) {
            chargePerItem = CHARGE_PER_EMERALD;
        } else if (stack.getType() == Material.EMERALD_BLOCK) {
            chargePerItem = CHARGE_PER_EMERALD * 9;
        } else {
            return 0;
        }

        final var maxToConsume = maxCharge / chargePerItem;
        final var stackAmount = stack.getAmount();
        if (maxToConsume >= stackAmount) {
            // Empty this stack completely and return charge from this stack's amount
            stack.setType(Material.AIR);
            return stackAmount * chargePerItem;
        } else {
            // maxToConsume < stackAmount
            // So subtract this stack's amount by the maximum to consume.
            stack.setAmount(stackAmount - maxToConsume);
            return maxToConsume * chargePerItem;
        }
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
