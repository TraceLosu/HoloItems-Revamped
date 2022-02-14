package xyz.holocons.mc.holoitemsrevamp.enchant;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.ability.BlockBreak;

public class EnchantListener implements Listener {

    private final HoloItemsRevamp plugin;

    public EnchantListener(HoloItemsRevamp plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        var player = event.getPlayer();
        var itemStack = player.getInventory().getItemInMainHand();

        if (!itemStack.hasItemMeta()) {
            return;
        }

        var enchants = itemStack.getItemMeta().getEnchants();
        enchants.keySet().forEach(enchantment -> {
            if (enchantment instanceof BlockBreak blockBreak) {
                blockBreak.run(event);
            }
        });
    }

    //TODO add listener for anvil crafting, cuz custom enchants does not work by default.
}
