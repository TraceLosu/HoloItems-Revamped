package xyz.holocons.mc.holoitemsrevamp.enchantment;

import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantmentAbility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Container;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;


import java.util.Map;

public class TimeLapseroni extends CustomEnchantment implements EnchantmentAbility {

    private final HoloItemsRevamp plugin;

    public TimeLapseroni(HoloItemsRevamp plugin) {
        super(plugin, "time_lapseroni");
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
        return item.getType() == Material.CLOCK;
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text("Time Lapseroni", NamedTextColor.GOLD)
            .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public int getCostMultiplier() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event, ItemStack itemStack) {

        final var clickedBlock = event.getClickedBlock();

        // If the clicked block is null, return
        if (clickedBlock == null){
            return;
        }

        // If not right-clicked or a container, return
        final var blockState = clickedBlock.getState();
        if(!event.getAction().isRightClick() || !(blockState instanceof Container)){
            return;
        }

        Container container = (Container) blockState;

        Map<ItemStack, Material> metals = Map.of(
            new ItemStack(Material.COPPER_INGOT),    Material.RAW_COPPER,
            new ItemStack(Material.IRON_INGOT),      Material.RAW_IRON,
            new ItemStack(Material.GOLD_INGOT),      Material.RAW_GOLD,
            new ItemStack(Material.NETHERITE_SCRAP), Material.ANCIENT_DEBRIS,
            new ItemStack(Material.IRON_BLOCK),      Material.RAW_IRON_BLOCK,
            new ItemStack(Material.GOLD_BLOCK),      Material.RAW_GOLD_BLOCK,
            new ItemStack(Material.COPPER_BLOCK),    Material.RAW_COPPER_BLOCK
        );


        // Iterate over the container and if an item exists and its type is one of the above, replace it.
        boolean consumeItem = false;
        for (ItemStack item : container.getInventory()) {
            if (item == null) {
                continue;
            }
            for (ItemStack metal : metals.keySet()) {
                    if (item.isSimilar(metal)) {
                        item.setType(metals.get(metal));
                        consumeItem = true;
                    }
                }

        }

        // Deletes item if it was used.
        if(consumeItem){
            final Player player = event.getPlayer();
            final World world = player.getWorld();

            world.playSound(player.getLocation(), Sound.BLOCK_BELL_RESONATE, 0.1f, 0.1f );
            event.getItem().setAmount(0);
        }
    }
}

