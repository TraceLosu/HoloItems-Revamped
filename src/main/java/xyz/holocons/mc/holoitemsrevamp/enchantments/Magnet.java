package xyz.holocons.mc.holoitemsrevamp.enchantments;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.enchant.CustomEnchant;
import xyz.holocons.mc.holoitemsrevamp.enchant.interfaces.Mineable;

public class Magnet extends CustomEnchant implements Mineable {

    private final HoloItemsRevamp plugin;

    public Magnet(HoloItemsRevamp plugin) {
        super(plugin, "magnet");
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getName() {
        return "magnet";
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
        return false;
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text("Magnet I", NamedTextColor.GRAY);
    }

    @Override
    public void run(BlockBreakEvent event) {
        final var location = event.getBlock().getLocation();
        final var player = event.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                final var items = location.getNearbyEntitiesByType(Item.class, 1.5);
                final var itemStacks = items.stream().map(item -> item.getItemStack()).toArray(ItemStack[]::new);
                final var excess = player.getInventory().addItem(itemStacks);
                excess.values().forEach(itemStack -> player.getWorld().dropItemNaturally(player.getLocation(), itemStack));
                items.forEach(Item::remove);
            }
        }.runTask(plugin);
    }
}
