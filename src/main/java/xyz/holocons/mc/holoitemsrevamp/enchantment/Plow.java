package xyz.holocons.mc.holoitemsrevamp.enchantment;

import com.destroystokyo.paper.MaterialTags;
import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantmentAbility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.HashMap;
import java.util.Map;

public class Plow extends CustomEnchantment implements EnchantmentAbility{

    // States the tick when each Player can start breaking non-snow blocks again
    private final Map<Player, Integer> canBreakBlockTick = new HashMap<>();

    public Plow(HoloItemsRevamp plugin){
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
        int currentTick = Bukkit.getCurrentTick();
        Player player = event.getPlayer();
        // If this was me, I'd probably also get SNOW_BLOCK? But to mirror old functionality,
        // I'm going to target only SNOW.
        if(event.getBlock().getType() == Material.SNOW){
            // Event broke snow
            // Stop other block-breaks for 20 ticks
            canBreakBlockTick.put(player, currentTick+20);
        }
        else{
            // Event did not break snow
            // What tick to resume breaking non-snow blocks?
            // (Presuming tick -1 is illegal)
            int stopBlockBreaksUntil = canBreakBlockTick.getOrDefault(player, -1);
            if(currentTick <= stopBlockBreaksUntil){
                event.setCancelled(true);
            }
        }
    }
}
