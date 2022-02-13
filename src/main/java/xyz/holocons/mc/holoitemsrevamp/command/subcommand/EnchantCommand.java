package xyz.holocons.mc.holoitemsrevamp.command.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.command.SubCommand;

import java.util.List;

public class EnchantCommand implements SubCommand {

    private final HoloItemsRevamp plugin;

    public EnchantCommand(HoloItemsRevamp plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "enchant";
    }

    @Override
    public String getDesc() {
        return "Enchants an item in your hand";
    }

    @Override
    public String getFormat() {
        return "<enchant>";
    }

    @Override
    public String getPermission() {
        return "holoitems.enchant";
    }

    @Override
    public List<String> getAutoComplete(String[] args) {
        return List.of();
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage("You do not have the permission to use this command!");
            return true;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Don't use this command in console.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("Not enough arguments");
            return false;
        }

        var itemStack = player.getInventory().getItemInMainHand();
        var itemStackMeta = itemStack.getItemMeta();
        var customEnchant = plugin.getEnchantManager().getCustomEnchant(args[0]);

        if (customEnchant == null) {
            player.sendMessage(args[0] + " is not a valid item!");
            return false;
        }

        if (itemStackMeta.addEnchant(customEnchant, 1, false)) {
            itemStack.setItemMeta(itemStackMeta);
            player.sendMessage("Enchanted!");
        } else {
            player.sendMessage("Could not set enchant!");
        }
        return true;
    }
}
