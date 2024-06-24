package xyz.holocons.mc.holoitemsrevamp.enchantment;

import com.destroystokyo.paper.MaterialTags;
import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantmentAbility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.util.EntityExpiringSet;

public class Plow extends CustomEnchantment implements EnchantmentAbility {

    private final EntityExpiringSet plowMarker = new EntityExpiringSet(
            new EntityExpiringSet.ConstantTicksToLiveExpirationPolicy<>(20));

    public Plow(HoloItemsRevamp plugin) {
        super(plugin, "plow");
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
        return MaterialTags.SHOVELS.isTagged(itemStack);
    }

    @Override
    public @NotNull Component displayName(int i) {
        return Component.text()
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
                .append(Component.text("Plow"))
                .build();
    }

    @Override
    public int getCostMultiplier() {
        // Copied from Magnet
        return 12;
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, ItemStack itemStack) {
        final var player = event.getPlayer();
        if (event.getBlock().getType() == Material.SNOW) {
            plowMarker.add(player);
        } else {
            event.setCancelled(plowMarker.test(player));
        }
    }
}
