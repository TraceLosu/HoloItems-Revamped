package com.strangeone101.holoitemsapi.enchantment;

import com.strangeone101.holoitemsapi.item.CustomItemManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrowableProjectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Repairable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.ability.BlockBreak;
import xyz.holocons.mc.holoitemsrevamp.ability.BlockPlace;
import xyz.holocons.mc.holoitemsrevamp.ability.PlayerDeath;
import xyz.holocons.mc.holoitemsrevamp.ability.PlayerInteract;
import xyz.holocons.mc.holoitemsrevamp.ability.ProjectileLaunch;
import xyz.holocons.mc.holoitemsrevamp.integration.IntegrationManager;
import xyz.holocons.mc.holoitemsrevamp.integration.WorldGuardHook;
import xyz.holocons.mc.holoitemsrevamp.packet.PlayerAbilitiesPacket;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnchantListener implements Listener {

    private final static int MAX_REPAIR_COST = Short.MAX_VALUE;

    private final HoloItemsRevamp plugin;
    private final WorldGuardHook worldGuard;

    public EnchantListener(HoloItemsRevamp plugin) {
        this.plugin = plugin;
        this.worldGuard = IntegrationManager.getWorldGuard();
    }

    /**
     * Handles BlockBreak enchantments.
     *
     * @param event The BlockBreakEvent
     */
    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        final var itemStack = event.getPlayer().getInventory().getItemInMainHand();

        itemStack.getEnchantments().keySet().forEach(enchantment -> {
            if (enchantment instanceof BlockBreak ability) {
                final var location = event.getBlock().getLocation();
                if (!worldGuard.testAbilityAllowed(location, BlockBreak.class)) {
                    event.setCancelled(true);
                    return;
                }

                ability.run(event, itemStack);
            }
        });
    }

    /**
     * Handles BlockPlace enchantments.
     *
     * @param event The BlockPlaceEvent
     */
    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        final var itemStack = event.getItemInHand();

        itemStack.getEnchantments().keySet().forEach(enchantment -> {
            if (enchantment instanceof BlockPlace ability) {
                final var location = event.getBlock().getLocation();
                if (!worldGuard.testAbilityAllowed(location, BlockPlace.class)) {
                    event.setCancelled(true);
                    return;
                }

                ability.run(event, itemStack);
            }
        });
    }

    /**
     * Handles PlayerDeath enchantments.
     *
     * @param event The PlayerDeathEvent
     */
    @EventHandler(ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        final var location = event.getPlayer().getLocation();
        if (!worldGuard.testAbilityAllowed(location, PlayerDeath.class)) {
            return;
        }

        final var playerInventory = event.getPlayer().getInventory();

        for (final var itemStack : playerInventory) {
            itemStack.getEnchantments().keySet().forEach(enchantment -> {
                if (enchantment instanceof PlayerDeath ability) {
                    ability.run(event, itemStack);
                }
            });
        }
    }

    /**
     * Handles PlayerInteract enchantments.
     *
     * @param event The PlayerInteractEvent
     */
    @EventHandler(ignoreCancelled = false)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.isBlockInHand() || !event.getAction().isRightClick() || !event.hasItem()) {
            return;
        }
        final var itemStack = switch (event.getHand()) {
            case HAND -> event.getPlayer().getInventory().getItemInMainHand();
            case OFF_HAND -> event.getPlayer().getInventory().getItemInOffHand();
            default -> new ItemStack(Material.AIR);
        };

        itemStack.getEnchantments().keySet().forEach(enchantment -> {
            if (enchantment instanceof PlayerInteract ability) {
                final var location = event.getPlayer().getLocation();
                if (!worldGuard.testAbilityAllowed(location, PlayerInteract.class)) {
                    event.setCancelled(true);
                    return;
                }

                ability.run(event, itemStack);
            }
        });
    }

    /**
     * Handles ProjectileLaunch enchantments.
     *
     * @param event The ProjectileLaunchEvent
     */
    @EventHandler(ignoreCancelled = true)
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (!(event.getEntity() instanceof ThrowableProjectile throwableProjectile)) {
            return;
        }
        final var itemStack = throwableProjectile.getItem();

        itemStack.getEnchantments().keySet().forEach(enchantment -> {
            if (enchantment instanceof ProjectileLaunch ability) {
                final var location = event.getLocation();
                if (!worldGuard.testAbilityAllowed(location, ProjectileLaunch.class)) {
                    event.setCancelled(true);
                    return;
                }

                ability.run(event, itemStack);
            }
        });
    }

    /**
     * Makes sure that after a player closes an anvil inventory, their instant build
     * ability gets disabled.
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory() instanceof AnvilInventory) ||
                !(event.getPlayer() instanceof Player player) ||
                player.getGameMode() == GameMode.CREATIVE)
            return;

        new PlayerAbilitiesPacket(player, false).sendPacket(player);
    }

    /**
     * Handles anvil craftings regarding custom enchantments and custom items.
     */
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        // Make sure viewer is a player and they don't have creative bypasses
        if (!(event.getView().getPlayer() instanceof Player player) || player.getGameMode() == GameMode.CREATIVE)
            return;

        var inventory = event.getInventory();

        var base = inventory.getFirstItem();
        var addition = inventory.getSecondItem();

        if (base == null || !(base.getItemMeta() instanceof Repairable) || base.getAmount() != 1)
            return;

        // Only handle events that contain custom enchantments
        if (hasNoCustomEnchants(base) && hasNoCustomEnchants(addition))
            return;

        if (CustomItemManager.isCustomItem(base)) { // Disallow players to modify custom items
            event.setResult(null);
            plugin.getServer().getScheduler().runTask(plugin, () -> inventory.setResult(null));
            return;
        }

        inventory.setMaximumRepairCost(MAX_REPAIR_COST);

        // Name handling with base item containing custom enchantments
        if (addition == null) {
            var renameText = inventory.getRenameText();
            var displayName = base.getItemMeta().hasDisplayName()
                    ? PlainTextComponentSerializer.plainText().serialize(base.getItemMeta().displayName())
                    : "";
            if (renameText != null && !renameText.isBlank() && !displayName.equals(renameText)) {
                var result = base.clone();
                var resultMeta = result.getItemMeta();

                resultMeta.displayName(Component.text(renameText));
                result.setItemMeta(resultMeta);

                final int levelCost = inventory.getRepairCost();

                plugin.getServer().getScheduler().runTask(plugin, () -> {
                    if (!base.equals(inventory.getFirstItem()))
                        return;

                    inventory.setResult(result);
                    inventory.setRepairCost(levelCost);
                    player.setWindowProperty(InventoryView.Property.REPAIR_COST, levelCost);
                    new PlayerAbilitiesPacket(player, hasEnoughLevels(player, levelCost)).sendPacket(player);
                });
            }
            return;
        }

        // Enchantment handling if addition item is an enchanted book
        if (addition.getType() == Material.ENCHANTED_BOOK) {
            var customEnchants = combineCustomEnchants(base, addition);
            var result = event.getResult();
            int levelCost = inventory.getRepairCost();

            if (customEnchants.isEmpty())
                return;

            // Enchantment handling if the enchanted book contains only custom enchantments
            if (result == null) {
                result = base.clone();
                levelCost = ((Repairable) base.getItemMeta()).getRepairCost();
            }

            customEnchants.forEach(result::addEnchantment);
            plugin.getEnchantManager().removeCustomEnchantmentLore(result);
            plugin.getEnchantManager().applyCustomEnchantmentLore(result);

            final var finalLevelCost = getCustomEnchantCost(customEnchants, levelCost);
            final var finalResult = result;

            inventory.setResult(finalResult);
            inventory.setRepairCost(finalLevelCost);

            plugin.getServer().getScheduler().runTask(plugin, () -> {
                if (!base.equals(inventory.getFirstItem()) || !addition.equals(inventory.getSecondItem()))
                    return;

                inventory.setResult(finalResult);
                inventory.setRepairCost(finalLevelCost);
                player.setWindowProperty(InventoryView.Property.REPAIR_COST, finalLevelCost);
                new PlayerAbilitiesPacket(player, hasEnoughLevels(player, finalLevelCost)).sendPacket(player);
            });
            return;
        }

        // Enchantment handling if the addition is not an enchanted book
        if (CustomItemManager.isCustomItem(addition)) {
            // It's a custom item, but not an enchanted book. Should not be used to
            // enchant/repair stuff
            event.setResult(null);
            plugin.getServer().getScheduler().runTask(plugin, () -> inventory.setResult(null));
            return;
        }

        var result = inventory.getResult();
        if (result == null) {
            return;
        }

        var customEnchants = combineCustomEnchants(base, addition);
        var levelCost = getCustomEnchantCost(customEnchants, inventory.getRepairCost());

        customEnchants.forEach(result::addEnchantment);
        plugin.getEnchantManager().removeCustomEnchantmentLore(result);
        plugin.getEnchantManager().applyCustomEnchantmentLore(result);

        event.setResult(result);
        inventory.setRepairCost(levelCost);

        plugin.getServer().getScheduler().runTask(plugin, () -> {
            if (!base.equals(inventory.getFirstItem()) || !addition.equals(inventory.getSecondItem()))
                return;

            inventory.setResult(result);
            inventory.setRepairCost(levelCost);
            player.setWindowProperty(InventoryView.Property.REPAIR_COST, levelCost);
            new PlayerAbilitiesPacket(player, hasEnoughLevels(player, levelCost)).sendPacket(player);
        });
    }

    private static boolean hasEnoughLevels(Player player, int repairCost) {
        return player.getLevel() >= repairCost;
    }

    /**
     * Combines custom enchantments from two items while handling conflicts and
     * levels.
     * 
     * @param base     The base item
     * @param addition The second item
     * @return A map containing all custom enchants
     */
    private static Map<CustomEnchantment, Integer> combineCustomEnchants(@NotNull ItemStack base,
            @NotNull ItemStack addition) {
        final var baseEnchants = EnchantManager.getEnchantments(base);
        final var additionEnchants = EnchantManager.getEnchantments(addition).entrySet().stream()
                .filter(entry -> hasNoConflictEnchants(baseEnchants, entry.getKey())
                        && entry.getKey().canEnchantItem(base))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return combineCustomEnchants(baseEnchants, additionEnchants);
    }

    private static Map<CustomEnchantment, Integer> combineCustomEnchants(Map<Enchantment, Integer> base,
            Map<Enchantment, Integer> addition) {
        final var combined = Stream.of(base, addition)
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .filter(entry -> entry.getKey() instanceof CustomEnchantment)
                .collect(Collectors.toMap(entry -> (CustomEnchantment) entry.getKey(), Map.Entry::getValue,
                        (a, b) -> a.equals(b) ? a + 1 : Integer.max(a, b)));
        final var maxLevels = combined.keySet()
                .stream()
                .collect(Collectors.toMap(Function.identity(), Enchantment::getMaxLevel));
        return Stream.of(combined, maxLevels)
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::min));
    }

    /**
     * Get the total enchantment cost from the map of enchantments.
     * 
     * @param enchantments Enchantments to calculate level cost
     * @param initialCost  Repair cost before custom enchantments are considered
     * @return Level cost to add enchantments to an item
     */
    private static int getCustomEnchantCost(Map<CustomEnchantment, Integer> enchantments, int initialCost) {
        return enchantments.entrySet().stream()
                .reduce(initialCost, EnchantListener::levelCostAccumulator, Integer::sum);
    }

    private static int levelCostAccumulator(int partialCost, Map.Entry<CustomEnchantment, Integer> nextEnchantment) {
        return partialCost + Integer.min(nextEnchantment.getKey().getCostMultiplier(), MAX_REPAIR_COST)
                * nextEnchantment.getValue();
    }

    private static boolean hasNoConflictEnchants(Map<Enchantment, Integer> enchantments, Enchantment other) {
        return enchantments.keySet().stream().noneMatch(other::conflictsWith);
    }

    private static boolean hasNoCustomEnchants(@Nullable ItemStack itemStack) {
        return itemStack == null || !itemStack.hasItemMeta()
                || EnchantManager.getEnchantments(itemStack).keySet().stream()
                        .noneMatch(entry -> entry instanceof CustomEnchantment);
    }
}
