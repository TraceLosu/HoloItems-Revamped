package xyz.holocons.mc.holoitemsrevamp.enchantment;

import com.destroystokyo.paper.MaterialSetTag;
import com.destroystokyo.paper.MaterialTags;
import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantmentAbility;
import net.kyori.adventure.text.BlockNBTComponent;
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

import java.util.BitSet;
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

    // TYSM: https://stackoverflow.com/a/34363187
    private int[] get3DCoords(int id, int maxX, int maxY){
        int z = id/(maxX*maxY);
        id -= (z*maxX*maxY);
        int y =id/maxX;
        int x = id%maxX;
        return new int[] {x, y, z};
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event, ItemStack itemStack) {

        Block clickedBlock = event.getClickedBlock();

        // If not right-clicked or a container, return
        if(!event.getAction().isRightClick() || event.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }

        // If the block right-clicked is an interactable block, return
        if(clickedBlock.getBlockData().getMaterial().isInteractable()){
            return;
        }

        itemStack.setItemMeta(null);
        itemStack.setType(Material.BUCKET);

        Player player = event.getPlayer();
        player.setFreezeTicks(60);
        Location center = player.getLocation();
        player.getWorld().spawnParticle(Particle.WHITE_ASH, center, 200, 5, 5, 5);


        Random r = new Random();
        // 125 = 5^3, or the dimensions of the AoE
        // To change AoE to 8x8x8, change 125 to 8*8*8 (512) and get3DCoords' maxX and maxY accordingly
        BitSet visitedKeys = new BitSet(125);
        // Get origin to center AoE to center
        Location origin = clickedBlock.getLocation().add(-2, -2, -2);


        for (int i = 0; i < 25; i++) {
            int blockKey = r.nextInt(125);
            if (!visitedKeys.get(blockKey)){
                visitedKeys.set(blockKey);
                int[] coords = get3DCoords(blockKey, 5, 5);
                Block block = origin.clone().add(coords[0], coords[1], coords[2]).getBlock();
                if (block.getBlockData().isRandomlyTicked()){
                    block.randomTick();
                    plugin.getLogger().info(block.translationKey()+" at "+block.getLocation().toString());
                }
            }
        }
    }
}

