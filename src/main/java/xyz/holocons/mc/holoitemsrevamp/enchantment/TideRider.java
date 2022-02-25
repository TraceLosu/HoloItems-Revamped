package xyz.holocons.mc.holoitemsrevamp.enchantment;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.ability.PlayerInteract;
import xyz.holocons.mc.holoitemsrevamp.enchant.CustomEnchantment;

public class TideRider extends CustomEnchantment implements PlayerInteract {

    private final HoloItemsRevamp plugin;

    public TideRider(HoloItemsRevamp plugin) {
        super(plugin, "tide_rider");
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
    public void run(PlayerInteractEvent event, ItemStack itemStack) {

        new BukkitRunnable() {
            final Player player = event.getPlayer();
            final World world = player.getWorld();
            final ItemMeta meta = itemStack.getItemMeta();
            int increment = 0;
            @Override
            public void run() {
                Location location = player.getLocation();
                Vector direction = location.getDirection().setY(0.0001).normalize();
                Block front = world.getBlockAt(location.clone().add(direction));
                Block below = world.getBlockAt(location.add(0, -1, 0));

                if (!player.isValid() || !player.isHandRaised() || increment>=1200){
                    meta.removeEnchant(Enchantment.RIPTIDE);
                    meta.addEnchant(Enchantment.LOYALTY, 3, false);
                    Damageable dmeta = (org.bukkit.inventory.meta.Damageable) itemStack.getItemMeta();
                    dmeta.setDamage(dmeta.getDamage()+1);
                    itemStack.setItemMeta(meta);
                    System.out.println("Cancel");
                    System.out.println(increment);
                    System.out.println(player.isValid());
                    System.out.println(player.isHandRaised());
                    cancel();
                }
                else if(increment==0){
                    meta.removeEnchant(Enchantment.LOYALTY);
                    meta.addEnchant(Enchantment.RIPTIDE, 3, false);
                    itemStack.setItemMeta(meta);
                }
                increment ++;

                double multiplier = 1;
                if (player.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE))
                    multiplier += 0.33;
                if (below.getType() == Material.SOUL_SAND)
                    multiplier -= 0.33;
                ItemStack boots = player.getInventory().getBoots();
                if (boots == null || boots.getType() == Material.AIR)
                    ;
                else {
                    multiplier += 0.11 * boots.getEnchantmentLevel(Enchantment.DEPTH_STRIDER);
                    if (boots.getEnchantments().containsKey(Enchantment.SOUL_SPEED) && below.getType() == Material.SOUL_SAND)
                        multiplier += 0.33 * boots.getEnchantmentLevel(Enchantment.SOUL_SPEED);
                }


                double yvelocity;
                if (front.isPassable() && !front.isLiquid())
                    if (below.isPassable() && !below.isLiquid())
                        yvelocity = -0.5;

                    else
                        yvelocity = 0;

                else
                if (!below.isPassable() && !below.isLiquid())
                    yvelocity = 0.5;

                else
                    yvelocity = 0;

                player.setVelocity(player.getVelocity().add(direction).normalize().multiply(multiplier)
                    .setY(yvelocity));


                player.setFallDistance(0);
            }
        }.runTaskTimer(plugin, 0, 1);
    }
}
