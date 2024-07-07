package xyz.holocons.mc.holoitemsrevamp.enchantment;

import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantmentAbility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public class GemKnife extends CustomEnchantment implements EnchantmentAbility {

    private final HoloItemsRevamp plugin;

    public GemKnife(HoloItemsRevamp plugin) {
        super(plugin, "gem_knife");
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
        // TODO
    }
}
