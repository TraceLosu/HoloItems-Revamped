package xyz.holocons.mc.holoitemsrevamp.command.subcommand;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.command.SubCommand;

import java.util.List;

public class CollectionsCommand implements SubCommand {

    private final HoloItemsRevamp plugin;

    public CollectionsCommand(HoloItemsRevamp plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "collections";
    }

    @Override
    public String getDesc() {
        return "Open a GUI to explore all available items";
    }

    @Override
    public String getFormat() {
        return "[player]";
    }

    @Override
    public String getPermission() {
        return "holoitems.collections";
    }

    @Override
    public List<String> getAutoComplete(String[] args) {
        return switch (args.length) {
            case 1 -> null;
            default -> List.of();
        };
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        OfflinePlayer targetPlayer;
        if(!(sender instanceof Player player)) {
            // Can't show gui to non-players
            sender.sendMessage(Component.text("Do not use this command as console.", NamedTextColor.YELLOW));
            return true;
        }

        if (!player.hasPermission(getPermission()) || (args.length < 1 && !player.hasPermission(getPermission() + ".others"))) {
            player.sendMessage(Component.text("You do not have permission to use this command!", NamedTextColor.RED));
            return true;
        }

        if (args.length < 1) {
            targetPlayer = (OfflinePlayer) player;
        } else {
            targetPlayer = Bukkit.getOfflinePlayerIfCached(args[0]);
            // Bukkit#getOfflinePlayerIfCached returns null if the player hasn't played on the server before
            if (targetPlayer == null) {
                player.sendMessage(Component.text("Player not found!", NamedTextColor.YELLOW));
                return false;
            }
        }

        // Create panes and guis
        var gui = new ChestGui(6, ComponentHolder.of(Component.text("HoloItems", NamedTextColor.AQUA, TextDecoration.BOLD)));
        var mainPane = new PaginatedPane(0, 0, 9, 6); // Pane for handling gen page and idol page
        var genPane = new PaginatedPane(1, 1, 7, 4); // Pane for handling gen page
        var pageButtonPane = new StaticPane(1, 5, 7, 1); // Pane for handling prev/next button on gen pane
        var backButtonPane = new StaticPane(4, 0, 1 ,1); // Pane for handling back button on idol page
        var idolPane = new OutlinePane(4, 1, 1, 1); // Pane for handling idol head on idol page
        var itemPane = new OutlinePane(0, 3, 9, 3); // Pane for handling custom items on idol page

        // Create buttons for gui
        var nextButton = new GuiItem(new ItemStack(Material.ARROW));
        var prevButton = new GuiItem(new ItemStack(Material.ARROW));
        var backButton = new GuiItem(new ItemStack(Material.ARROW));

        nextButton.setAction(event -> {
            event.setCancelled(true);
            if (genPane.getPage() < genPane.getPages() - 1) {
                genPane.setPage(genPane.getPage() + 1);

                if (genPane.getPage() == genPane.getPages() - 1)
                    nextButton.setVisible(false);

                if (genPane.getPage() != 0 && !prevButton.isVisible())
                    prevButton.setVisible(true);
                gui.update();
            }
        });
        var nextButtonMeta = nextButton.getItem().getItemMeta();
        nextButtonMeta.displayName(Component.text("Next page", NamedTextColor.DARK_PURPLE));
        nextButton.getItem().setItemMeta(nextButtonMeta);
        pageButtonPane.addItem(nextButton, 6, 0);

        prevButton.setAction(event -> {
            event.setCancelled(true);
            if (genPane.getPage() > 0) {
                genPane.setPage(genPane.getPage() - 1);

                if (genPane.getPage() == 0)
                    prevButton.setVisible(false);

                if (genPane.getPage() != genPane.getPages() - 1 && !nextButton.isVisible())
                    nextButton.setVisible(true);

                gui.update();
            }
        });
        var prevButtonMeta = prevButton.getItem().getItemMeta();
        prevButtonMeta.displayName(Component.text("Previous page", NamedTextColor.DARK_PURPLE));
        prevButton.getItem().setItemMeta(prevButtonMeta);
        // Set it to invisible because the command always puts you on the first page at the start
        prevButton.setVisible(false);
        pageButtonPane.addItem(prevButton, 0, 0);

        backButton.setAction(event -> {
            event.setCancelled(true);
            mainPane.setPage(0);
            idolPane.clear();
            itemPane.clear();
            gui.update();
        });
        var backButtonMeta = backButton.getItem().getItemMeta();
        backButtonMeta.displayName(Component.text("Back to menu", NamedTextColor.DARK_PURPLE));
        backButton.getItem().setItemMeta(backButtonMeta);
        backButtonPane.addItem(backButton, 0, 0);

        // Create ints for counting pages and slots for gen items
        int ySlot = 0;
        int page = 0;

        // Generate new GUI for every player
        for (var idolCollection : plugin.getCollectionManager().getAllGens()) {
            var outlinePane = new OutlinePane(0, ySlot, 7, 1);
            outlinePane.addItem(new GuiItem(idolCollection.getGuiItem()));
            idolCollection.getIdolSet().forEach(idol -> {
                var guiHeadItem = new GuiItem(idol.getHead());
                guiHeadItem.setAction(event -> {
                    event.setCancelled(true);
                    if (mainPane.getPage() == 0) {
                        idolPane.addItem(new GuiItem(idol.getHead()));
                        idol.getItemSet().forEach(item -> itemPane.addItem(new GuiItem(item.buildGuiStack(targetPlayer))));
                        mainPane.setPage(1);
                        gui.update();
                    }
                });
                outlinePane.addItem(guiHeadItem);
            });
            genPane.addPane(page, outlinePane);

            if (ySlot < 3) {
                ySlot++;
            } else {
                ySlot = 0;
                page++;
            }
        }

        mainPane.addPane(0, genPane);
        mainPane.addPane(0, pageButtonPane);
        mainPane.addPane(1, idolPane);
        mainPane.addPane(1, itemPane);
        mainPane.addPane(1, backButtonPane);
        gui.addPane(mainPane);
        gui.setOnGlobalClick(event -> event.setCancelled(true));
        gui.show(player);

        return true;
    }
}
