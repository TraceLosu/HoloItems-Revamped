package xyz.holocons.mc.holoitemsrevamp.enchantment;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.ability.BlockPlace;
import xyz.holocons.mc.holoitemsrevamp.ability.PlayerDeath;

public class Momento extends CustomEnchantment implements PlayerDeath, BlockPlace {

    private final HoloItemsRevamp plugin;

    public Momento(HoloItemsRevamp plugin) {
        super(plugin, "momento");
        this.plugin = plugin;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return item.getType() == Material.ENDER_CHEST;
        // Is there a better way to do this?
        // Not sure why, but I feel like this can be done better.
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text()
            .color(NamedTextColor.DARK_PURPLE)
            .decoration(TextDecoration.ITALIC, false)
            .append(Component.text("Momento"))
            .build();
    }

    @Override
    public int getCostMultiplier() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void run(PlayerDeathEvent event, ItemStack itemStack) {
        // Set keepInv and keepExp on (For this event and player only)
        if (event.getKeepInventory()){
            return;
        }

        event.setKeepInventory(true);
        event.setKeepLevel(true);

        // Clear corpse items to prevent dupes
        event.setShouldDropExperience(false);
        event.getDrops().clear();

        // Decrease amount of Momentos by 1
        itemStack.subtract(1);
    }

    @Override
    public void run(BlockPlaceEvent event, ItemStack itemStack){
        event.setCancelled(true);
    }
}
