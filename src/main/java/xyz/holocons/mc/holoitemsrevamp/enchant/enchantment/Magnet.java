package xyz.holocons.mc.holoitemsrevamp.enchant.enchantment;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import com.destroystokyo.paper.MaterialTags;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.Util;
import xyz.holocons.mc.holoitemsrevamp.ability.BlockBreak;
import xyz.holocons.mc.holoitemsrevamp.enchant.CustomEnchantment;
import xyz.holocons.mc.holoitemsrevamp.packet.EntityDestroyPacket;
import xyz.holocons.mc.holoitemsrevamp.packet.EntityMetadataPacket;
import xyz.holocons.mc.holoitemsrevamp.packet.SpawnEntityLivingPacket;

public class Magnet extends CustomEnchantment implements BlockBreak {

    private final HoloItemsRevamp plugin;

    public Magnet(HoloItemsRevamp plugin) {
        super(plugin, "magnet");
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
        return MaterialTags.PICKAXES.isTagged(item) || MaterialTags.AXES.isTagged(item)
            || MaterialTags.SHOVELS.isTagged(item) || MaterialTags.HOES.isTagged(item);
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text("Magnet I", NamedTextColor.GRAY)
            .decoration(TextDecoration.ITALIC, false);
    }

    @Override
    public int getItemStackCost(ItemStack itemStack) {
        int level = 0;

        var oreLevel = Util.getOreLevel(itemStack.getType());
        var enchantments = itemStack.getEnchantments();

        switch (oreLevel) {
            case NETHERITE_INGOT -> level = level + 10;
            case DIAMOND -> level = level + 8;
            case GOLD_INGOT -> level = level + 3;
            case IRON_INGOT -> level = level + 5;
            case OAK_PLANKS -> level = level + 1;
            default -> {}
        }

        for (var entry : enchantments.entrySet()) {
            if (Enchantment.DIG_SPEED.equals(entry.getKey())) {
                level = level + (5 * entry.getValue());
            } else if (Enchantment.DURABILITY.equals(entry.getKey())) {
                level = level + (3 * entry.getValue());
            } else if (Enchantment.SILK_TOUCH.equals(entry.getKey())) {
                level = level + 5;
            } else {
                level = level + 2;
            }
        }

        return level;
    }

    @Override
    public void run(BlockBreakEvent event, ItemStack itemStack) {
        final var location = event.getBlock().getLocation().toCenterLocation();
        final var player = event.getPlayer();

        final var entityId = Util.nextEntityId();
        final var uniqueId = Util.randomUUID();
        new SpawnEntityLivingPacket(entityId, uniqueId, EntityType.GUARDIAN, location).sendPacket(player);

        final var metadata = new EntityMetadataPacket.Metadata();
        metadata.setByte(0, (byte)0x20);                // invisible
        metadata.setByte(15, (byte)0x04);               // aggressive
        metadata.setVarInt(17, player.getEntityId());   // target player
        new EntityMetadataPacket(entityId, metadata).sendPacket(player);

        new BukkitRunnable() {
            @Override
            public void run() {
                final var items = location.getNearbyEntitiesByType(Item.class, 1.5, Item::canPlayerPickup);
                final var itemStacks = items.stream().map(Item::getItemStack).toArray(ItemStack[]::new);
                final var excess = player.getInventory().addItem(itemStacks);
                items.forEach(player::playPickupItemAnimation);
                items.forEach(Item::remove);
                excess.values().forEach(itemStack -> player.getWorld().dropItemNaturally(player.getLocation(), itemStack));
            }
        }.runTask(plugin);

        new BukkitRunnable() {
            @Override
            public void run() {
                new EntityDestroyPacket(entityId).sendPacket(player);
            }
        }.runTaskLater(plugin, 4);
    }
}
