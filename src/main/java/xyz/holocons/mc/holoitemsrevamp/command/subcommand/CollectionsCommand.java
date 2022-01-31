package xyz.holocons.mc.holoitemsrevamp.command.subcommand;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.command.SubCommand;

import java.util.List;

public class CollectionsCommand implements SubCommand {

    private final HoloItemsRevamp plugin;

    public CollectionsCommand(HoloItemsRevamp plugin){
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "collections";
    }

    @Override
    public String getDesc() {
        return "Opens a GUI to explore all available items.";
    }

    @Override
    public String getFormat() {
        return "[player]";
    }

    @Override
    public List<String> getAutoComplete() {
        return null; //Returning null cuz paper handles null autocomplete with player list, which is what we need.
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Do not use console with this command.");
            return true; //Can't show gui to non-players
        }
        ChestGui gui = new ChestGui(6, "HoloItem"); //Placeholder
        gui.show((HumanEntity) sender);
        return true;
    }
}
