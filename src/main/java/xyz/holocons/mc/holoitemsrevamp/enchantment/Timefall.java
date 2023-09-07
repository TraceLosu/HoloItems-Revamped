package xyz.holocons.mc.holoitemsrevamp.enchantment;

import com.destroystokyo.paper.MaterialSetTag;
import com.destroystokyo.paper.MaterialTags;
import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantmentAbility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Timefall extends CustomEnchantment implements EnchantmentAbility {

    private final HoloItemsRevamp plugin;

    public Timefall(HoloItemsRevamp plugin) {
        super(plugin, "timefall");
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
        return item.getType() == Material.POWDER_SNOW_BUCKET;
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text("Timefall", NamedTextColor.GOLD)
            .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public int getCostMultiplier() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event, ItemStack itemStack) {

        // If not right-clicked or a container, return
        if(!event.getAction().isRightClick() || event.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }

        ItemStack item = event.getItem();
        item.setType(Material.BUCKET);
        item.setItemMeta(new ItemStack(Material.BUCKET).getItemMeta());

        Player player = event.getPlayer();
        player.setFreezeTicks(60);
        Location center = player.getLocation();
        player.getWorld().spawnParticle(Particle.WHITE_ASH, center, 200, 5, 5, 5);

        int ticks = 25;
        boolean consumeItem = false;

        for (int x=-5; x<=5; x++) {
            for (int y=-5; y<=5; y++) {
                for(int z=-5; z<=5; z++) {
                    Block block = center.clone().add(x, y, z).getBlock();
                    if(block.getBlockData().isRandomlyTicked())
                    {
                        for (int i = 0; i < ticks; i++) {
                            block.randomTick();
                        }
                        consumeItem = true;
                    }
                }
            }
        }



        // Deletes item if it was used.
        if(consumeItem){
            event.getItem().subtract(1);
        }
    }
}

