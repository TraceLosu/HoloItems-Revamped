package xyz.holocons.mc.holoitemsrevamp.enchant.enchantment;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import com.strangeone101.holoitemsapi.Properties;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.Util;
import xyz.holocons.mc.holoitemsrevamp.ability.PlayerInteract;
import xyz.holocons.mc.holoitemsrevamp.enchant.CustomEnchantment;

public class TideRider extends CustomEnchantment implements PlayerInteract {

    private final static long cooldown = 100;

    private final HoloItemsRevamp plugin;

    public TideRider(HoloItemsRevamp plugin) {
        super(plugin, "tide_rider");
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getName() {
        return "tide_rider";
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
        return item.getType() == Material.TRIDENT;
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text("Tide Rider", NamedTextColor.GRAY)
            .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public int getItemStackCost(ItemStack itemStack) {
        return 1000;
    }

    @Override
    public void run(PlayerInteractEvent event) {
        var itemStack = event.getItem();
        var itemMeta = itemStack.getItemMeta();
        var persistentDataContainer = itemMeta.getPersistentDataContainer();
        var currentTick = Util.currentTimeTicks();
        var expirationTick = Properties.COOLDOWN.get(persistentDataContainer);
        if (currentTick < expirationTick) {
            return;
        }
        Properties.COOLDOWN.set(persistentDataContainer, currentTick + cooldown);
        itemStack.setItemMeta(itemMeta);
        var player = event.getPlayer();
        var location = player.getLocation();
        player.sendBlockChange(location, Material.WATER.createBlockData());
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendBlockChange(location, location.getBlock().getBlockData());
            }
        }.runTaskLater(plugin, cooldown);
        return;
    }
}
