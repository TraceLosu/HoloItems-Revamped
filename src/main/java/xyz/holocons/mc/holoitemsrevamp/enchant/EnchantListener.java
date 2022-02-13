package xyz.holocons.mc.holoitemsrevamp.enchant;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.enchant.interfaces.Mineable;

public class EnchantListener implements Listener {

    private final HoloItemsRevamp plugin;

    public EnchantListener(HoloItemsRevamp plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        //plugin.getLogger().info("Block break event executed!");
        var player = event.getPlayer();
        var itemStack = player.getInventory().getItemInMainHand();

        if (!itemStack.hasItemMeta()) {
            return;
        }

        var itemEnchants = itemStack.getItemMeta().getEnchants();
        itemEnchants.keySet().stream().filter(enchantment -> enchantment instanceof CustomEnchant).forEach(enchantment -> {
            //plugin.getLogger().info("Found enchantment!");
            var customEnchant = (CustomEnchant) enchantment;
            if (customEnchant instanceof Mineable mineable) {
                //plugin.getLogger().info("Executing enchantment method!");
                mineable.run(event);
            }
        });
    }

    //TODO add listener for anvil crafting, cuz custom enchants does not work by default.
}
