package xyz.holocons.mc.holoitemsrevamp.enchantment;

import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantmentAbility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public class Library extends CustomEnchantment implements EnchantmentAbility {

    private final HoloItemsRevamp plugin;

    public Library(HoloItemsRevamp plugin) {
        super(plugin, "library_of_memories");
        this.plugin = plugin;
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
        return itemStack.getType() == Material.SHULKER_BOX;
    }

    @Override
    public @NotNull Component displayName(int i) {
        return Component.text()
            .color(NamedTextColor.GRAY)
            .decoration(TextDecoration.ITALIC, false)
            .append(Component.text("Magnet"))
            .build();
    }

    @Override
    public int getCostMultiplier() {
        return 39;
    }

    @Override
    public void onPlayerDeath(PlayerDeathEvent event, ItemStack itemStack) {
        // TODO
        
    }
}