package xyz.holocons.mc.holoitemsrevamp.command.subcommand;

import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.command.SubCommand;
import com.strangeone101.holoitemsapi.enchantments.CustomEnchantment;

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
        return switch (args.length) {
            case 1 -> plugin.getEnchantManager().getCustomEnchantmentNames();
            default -> List.of();
        };
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Do not use this command as console.");
            return true;
        }

        if (!player.hasPermission(getPermission())) {
            player.sendMessage("You do not have the permission to use this command!");
            return true;
        }

        if (args.length < 1) {
            player.sendMessage("Not enough arguments");
            return false;
        }

        var itemStack = player.getInventory().getItemInMainHand();
        var itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) {
            player.sendMessage(Component.translatable("commands.enchant.failed.itemless", NamedTextColor.RED, Component.text(player.getName())));
            return true;
        }

        var key = NamespacedKey.fromString(args[0], plugin);
        var customEnchantment = CustomEnchantment.getByKey(key);

        if (customEnchantment == null) {
            player.sendMessage(args[0] + " is not a valid enchantment!");
            return false;
        }

        if (itemMeta.addEnchant(customEnchantment, 1, false)) {
            itemStack.setItemMeta(itemMeta);
            player.sendMessage("Enchanted!");
        } else {
            player.sendMessage("Could not set enchant!");
        }
        return true;
    }
}
