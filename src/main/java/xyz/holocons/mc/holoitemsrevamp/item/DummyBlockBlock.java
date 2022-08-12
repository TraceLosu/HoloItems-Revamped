package xyz.holocons.mc.holoitemsrevamp.item;

import com.strangeone101.holoitemsapi.item.BlockAbility;
import com.strangeone101.holoitemsapi.item.CustomItem;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.Util;

import java.util.List;

public class DummyBlockBlock extends CustomItem implements BlockAbility {

    private final static String name = "dummy_block";
    private final static Material material = Material.DROPPER;
    private final static Component displayName = Component.text("Dummy Block", NamedTextColor.GOLD);
    private final static List<Component> lore = List.of(
        Component.text("A dummy custom block used for testing.", NamedTextColor.AQUA)
            .decoration(TextDecoration.ITALIC, false)
    );

    public DummyBlockBlock(HoloItemsRevamp plugin) {
        super(plugin, name, material, displayName, lore);
        register();
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent event, BlockState blockState) {
        event.getPlayer().sendMessage(Component.text("You placed a custom block!"));
    }

    @Override
    public void onBlockBreak(BlockBreakEvent event, BlockState blockState) {
        event.getPlayer().sendMessage(Component.text("You broke a custom block!"));
    }

    @Override
    public void onBlockDispense(BlockDispenseEvent event, BlockState blockState) {
        var itemStack = new ItemStack(Material.PAPER);
        var itemMeta = itemStack.getItemMeta();

        itemMeta.displayName(Component.text("Current Tick", NamedTextColor.GOLD));
        itemMeta.lore(List.of(Component.text(Util.currentTimeTicks())));

        itemStack.setItemMeta(itemMeta);

        event.setItem(itemStack);
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent event, BlockState blockState) {
        if (event.getPlayer() instanceof Player player) {
            player.playSound(blockState.getLocation(), Sound.ENTITY_GOAT_SCREAMING_AMBIENT, 1.0f, 1.0f);
        }
    }
}
