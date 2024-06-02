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
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.integration.Integrations;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Splinter extends CustomEnchantment implements EnchantmentAbility {

    private final HoloItemsRevamp plugin;

    private final MaterialSetTag COMPATIBLE_MATERIALS;

    // Set of players who currently have Splinter active.
    private static final Set<Player> currentlySplintering = new HashSet<>();

    public Splinter(HoloItemsRevamp plugin){
        super(plugin, "splinter");
        this.plugin = plugin;
        var materialSetTagKey = new NamespacedKey(plugin, "splinter_materials");
        this.COMPATIBLE_MATERIALS = new MaterialSetTag(materialSetTagKey, Splinter::isCompatibleMaterial).lock();
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
        return MaterialTags.AXES.isTagged(itemStack);
    }

    @Override
    public @NotNull Component displayName(int i) {
        return Component.text()
            .color(NamedTextColor.GRAY)
            .decoration(TextDecoration.ITALIC, false)
            .append(Component.text("Splinter"))
            .build();
    }

    @Override
    public int getCostMultiplier() {
        return 12;
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, ItemStack itemStack) {
        final Player player = event.getPlayer();
        if(currentlySplintering.contains(player)){
            return;
        }

        Queue<Block> blocksToCheck = new LinkedList<>();
        // I don't think a set of checked blocks is necessary? If there's a 1 tick delay, anyway.
        // (OldHoloItems has a set of checked blocks declared here.)

        Block firstBlock = event.getBlock();
        if(isInvalidSplinterType(firstBlock.getType()) || isInvalidSplinterLocation(firstBlock.getLocation())){
            return;
        }

        new SplinterRunnable(1, firstBlock, player).runTaskTimer(plugin, 0, 1);
    }

    private boolean isInvalidSplinterType(Material type) {
        return !this.COMPATIBLE_MATERIALS.isTagged(type);
    }

    private boolean isInvalidSplinterLocation(Location loc){
        return Integrations.WORLDGUARD.canUseEnchantment(loc, Splinter.class);
    }

    private static boolean isCompatibleMaterial(Material material) {
        // Copied straight from OldHoloItems
        return Tag.LOGS.isTagged(material) || Tag.LEAVES.isTagged(material)
            || Tag.CRIMSON_STEMS.isTagged(material) || Tag.WARPED_STEMS.isTagged(material)
            || Tag.WART_BLOCKS.isTagged(material) || MaterialTags.MUSHROOM_BLOCKS.isTagged(material);
    }

    private class SplinterRunnable extends BukkitRunnable{
        private int remainingCharge;
        private final Material targetType;
        private final Queue<Block> blocksToCheck = new LinkedList<>();
        private final Player player;

        public SplinterRunnable(int enchLevel, Block firstBlock, Player player){
            // In the future, we could have this be based on enchLevel.
            // For now, mirror old functionality.
            remainingCharge = 32;
            targetType = firstBlock.getType();
            addAdjacentBlocks(firstBlock);
            currentlySplintering.add(player);
            this.player = player;
        }

        @Override
        public void run() {
            // Check if this Splinter should continue
            if(remainingCharge <= 0){
                currentlySplintering.remove(player);
                cancel();
                return;
            }

            // Search for a block for Splinter to break
            Block blockToBreak = null;
            while(!blocksToCheck.isEmpty() && blockToBreak == null){
                Block testBlock = blocksToCheck.remove();
                if(testBlock.getType() != targetType){
                    continue;
                }
                if(isInvalidSplinterLocation(testBlock.getLocation())){
                    continue;
                }
                blockToBreak = testBlock;
            }

            // Check if a block was found; stop if it wasn't
            if(blockToBreak == null){
                currentlySplintering.remove(player);
                cancel();
                return;
            }

            // Break block and apply more Splinter effect
            player.breakBlock(blockToBreak);
            addAdjacentBlocks(blockToBreak);
            remainingCharge -= 1;
        }

        private void addAdjacentBlocks(Block block){
            for(int i = -1; i <= 1; i++){
                for(int j = -1; j <= 1; j++){
                    for(int k = -1; k <= 1; k++){
                        if(i == 0 && j == 0 && k == 0){
                            continue;
                        }
                        Block newBlock = block.getRelative(i, j, k);
                        if(isInvalidSplinterLocation(block.getLocation())){
                            continue;
                        }
                        blocksToCheck.add(newBlock);
                    }
                }
            }
        }
    }
}
