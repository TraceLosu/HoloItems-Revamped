package xyz.holocons.mc.holoitemsrevamp.enchantment;

import java.util.BitSet;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantmentAbility;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

public class Timefall extends CustomEnchantment implements EnchantmentAbility {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private static final int[] TICK_COUNT_OPTIONs = { 60, 80, 100, 120 };

    public Timefall(HoloItemsRevamp plugin) {
        super(plugin, "timefall");
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

    private static Block getBlockByIndex(Block origin, int maxX, int maxY, int index) {
        final var z = index / (maxX * maxY);
        index -= (z * maxX * maxY);
        final var y = index / maxX;
        final var x = index % maxX;
        return origin.getRelative(x, y, z);
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event, ItemStack itemStack) {
        event.setUseItemInHand(Result.DENY);
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK
                && event.getClickedBlock().getBlockData().getMaterial().isInteractable()) {
            return;
        }

        itemStack.setItemMeta(null);
        itemStack.setType(Material.BUCKET);

        final var radius = RANDOM.nextInt(3) + 2;
        final var distance = (radius * 2) + 1;
        final var blockCount = distance * distance * distance;

        final var player = event.getPlayer();
        player.setFreezeTicks(60);
        player.getWorld().spawnParticle(Particle.WHITE_ASH,
                player.getX(), player.getY() + radius - 1, player.getZ(), 200,
                radius, radius, radius);

        final var visitedBlocks = new BitSet(blockCount);
        final var origin = player.getLocation().add(-radius, -1, -radius).getBlock();
        final var tickCount = TICK_COUNT_OPTIONs[RANDOM.nextInt(TICK_COUNT_OPTIONs.length)];
        for (var i = 0; i < tickCount; i++) {
            final var blockIndex = RANDOM.nextInt(blockCount);
            if (!visitedBlocks.get(blockIndex)) {
                visitedBlocks.set(blockIndex);
                final var block = getBlockByIndex(origin, distance, distance, blockIndex);
                if (block.getBlockData().isRandomlyTicked()) {
                    block.randomTick();
                }
            }
        }
    }
}
