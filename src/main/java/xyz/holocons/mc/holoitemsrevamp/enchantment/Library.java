package xyz.holocons.mc.holoitemsrevamp.enchantment;

import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantmentAbility;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public class Library extends CustomEnchantment implements EnchantmentAbility {

    public Library(HoloItemsRevamp plugin) {
        super(plugin, "library_of_memories");
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
    public boolean canEnchantItem(@NotNull ItemStack itemStack) {
        // TODO: Other colors of shulkerbox
        return itemStack.getType() == Material.SHULKER_BOX;
    }

    @Override
    public @NotNull Component displayName(int i) {
        return Component.text()
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
                .append(Component.text("Library of Memories"))
                .build();
    }

    @Override
    public int getCostMultiplier() {
        return 39;
    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent event, ItemStack itemStack) {
        if (!(itemStack.getItemMeta() instanceof BlockStateMeta blockStateMeta)
                || !(blockStateMeta.getBlockState() instanceof ShulkerBox shulkerBox)) {
            return;
        }

        for (final var inventoryItemStack : shulkerBox.getInventory()) {
            if(inventoryItemStack == null) {
                continue;
            }
            for (final var enchantment : inventoryItemStack.getEnchantments().keySet()) {
                if (enchantment instanceof Memento memento) {
                    memento.onPlayerDeath(event, inventoryItemStack);
                }
            }
        }

        blockStateMeta.setBlockState(shulkerBox);
        itemStack.setItemMeta(blockStateMeta);
    }
}
