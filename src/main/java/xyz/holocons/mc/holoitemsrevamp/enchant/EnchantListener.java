package xyz.holocons.mc.holoitemsrevamp.enchant;

import com.strangeone101.holoitemsapi.CustomItemRegistry;
import com.strangeone101.holoitemsapi.interfaces.Enchantable;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.ability.BlockBreak;
import xyz.holocons.mc.holoitemsrevamp.ability.PlayerInteract;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnchantListener implements Listener {

    private final HoloItemsRevamp plugin;
    private final EnchantManager enchantManager;

    public EnchantListener(HoloItemsRevamp plugin, EnchantManager enchantManager) {
        this.plugin = plugin;
        this.enchantManager = enchantManager;
    }

    private static <A extends Ability, E extends Event> void runAbilities(Class<A> abilityCls, E event, ItemStack itemStack) {
        if (!itemStack.hasItemMeta()) {
            return;
        }
        final var enchants = itemStack.getItemMeta().getEnchants();
        enchants.keySet().forEach(enchantment -> {
            if (abilityCls.isInstance(enchantment)) {
                abilityCls.cast(enchantment).run(event, itemStack);
            }
        });
    }

    /**
     * Handles BlockBreak enchantments.
     *
     * @param event The BlockBreakEvent
     */
    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        var itemStack = event.getPlayer().getInventory().getItemInMainHand();

        runAbilities(BlockBreak.class, event, itemStack);
    }

    @EventHandler(ignoreCancelled = false)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.isBlockInHand() || !event.getAction().isRightClick() || !event.hasItem()) {
            return;
        }
        ItemStack itemStack = switch (event.getHand()) {
            case HAND -> itemStack = event.getPlayer().getInventory().getItemInMainHand();
            case OFF_HAND -> itemStack = event.getPlayer().getInventory().getItemInOffHand();
            default -> new ItemStack(Material.AIR);
        };

        runAbilities(PlayerInteract.class, event, itemStack);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPrepareAnvil(PrepareAnvilEvent event) {

        var player = event.getView().getPlayer();

        // Avoid creative bypasses
        if (player.getGameMode() == GameMode.CREATIVE)
            return;

        var base = event.getInventory().getFirstItem();
        var addition = event.getInventory().getSecondItem();

        if (base == null || addition == null)
            return;

        // Only handle events that contain custom enchantments
        if (hasNoCustomEnchants(base) && hasNoCustomEnchants(addition))
            return;

        // Only handle recipes that would work in vanilla
        if ((event.getInventory().getResult() == null || event.getInventory().getResult().getType() == Material.AIR)) {
            if (CustomItemRegistry.isCustomItem(addition)) { // Unless that second item is a custom item
                var customItem = CustomItemRegistry.getCustomItem(addition);
                if (customItem instanceof Enchantable enchantable) { // And it must implement the enchantable interface.
                    var resultItem = enchantable.applyEnchantment(base);

                    if (resultItem == null) // Applying enchantment failed
                        return;

                    var resultItemMeta = resultItem.getItemMeta();
                    int levelCost = ((CustomEnchantment) enchantable.getEnchantment()).getItemStackCost(resultItem);

                    var renameText = event.getInventory().getRenameText();
                    if (renameText != null && !renameText.equals("")) { // The player is trying to rename
                        ++levelCost;
                        resultItemMeta.displayName(PlainTextComponentSerializer.plainText().deserialize(renameText));
                        resultItem.setItemMeta(resultItemMeta);
                    }

                    event.setResult(resultItem);

                    final int finalLevelCost = levelCost;
                    final var finalBase = base.clone();
                    final var finalAddition = addition.clone();

                    plugin.getServer().getScheduler().runTask(plugin, () -> {
                        if (!finalBase.equals(event.getInventory().getFirstItem()) || !finalAddition.equals(event.getInventory().getSecondItem()))
                            return;

                        event.getInventory().setRepairCost(finalLevelCost);
                        event.getInventory().setResult(resultItem);
                        player.setWindowProperty(InventoryView.Property.REPAIR_COST, finalLevelCost);
                    });
                }
            }
            return;
        }

        var result = event.getInventory().getResult();
        var customEnchants = combineCustomEnchants(base, addition);
        result.addEnchantments(customEnchants);

        event.setResult(result);
        plugin.getServer().getScheduler().runTask(plugin, () -> {
            event.getInventory().setResult(result); // TODO add result cost from custom enchants
        });
    }

    /**
     * Combines custom enchantments from two items while handling conflicts and levels.
     * @param base The base item
     * @param addition The second item
     * @return A map containing all custom enchants
     */
    private static Map<Enchantment, Integer> combineCustomEnchants(@NotNull ItemStack base, @NotNull ItemStack addition) {
        var additionMeta = addition.getItemMeta();

        var baseEnchantments = base.getEnchantments();

        if (additionMeta instanceof EnchantmentStorageMeta) {
            var additionEnchants = ((EnchantmentStorageMeta) additionMeta).getStoredEnchants().entrySet().stream()
                .filter(entry -> hasNoConflictEnchants(baseEnchantments, entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            return combineCustomEnchants(base.getEnchantments(), additionEnchants);
        }

        var additionEnchants = addition.getEnchantments().entrySet().stream()
            .filter(entry -> hasNoConflictEnchants(baseEnchantments, entry.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return combineCustomEnchants(baseEnchantments, additionEnchants);
    }

    private static Map<Enchantment, Integer> combineCustomEnchants(Map<Enchantment, Integer> base, Map<Enchantment, Integer> addition) {
        return Stream.of(base, addition)
            .map(Map::entrySet)
            .flatMap(Set::stream)
            .filter(entry -> entry.getKey() instanceof CustomEnchantment)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
                if (a.equals(b)) return a+1;
                else return Integer.max(a, b);
            }));
    }

    private static boolean hasNoConflictEnchants(Map<Enchantment, Integer> enchants, Enchantment filter) {
        return enchants.keySet().stream()
            .noneMatch(filter::conflictsWith);
    }

    private static boolean hasNoCustomEnchants(@NotNull ItemStack itemStack) {
        return itemStack.getEnchantments().keySet().stream()
            .noneMatch(enchant -> enchant instanceof CustomEnchantment);
    }
}
