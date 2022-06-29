package com.strangeone101.holoitemsapi.item;

import com.strangeone101.holoitemsapi.Keys;
import com.strangeone101.holoitemsapi.Property;
import com.strangeone101.holoitemsapi.util.ItemUtils;
import com.strangeone101.holoitemsapi.util.StatsWrapper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.checkerframework.checker.nullness.qual.NonNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.Util;

import java.util.*;
import java.util.function.Function;

/**
 * A class for creating custom items. Be sure to call {@link #register()} after creating it
 * to properly register it
 */
public class CustomItem implements Keyed {
    private NamespacedKey key;
    private int internalIntID;

    private Material material;
    private Component displayName;
    private List<Component> lore = new ArrayList<>();
    private int cooldown = 0;
    private boolean stackable = true;
    private Set<Property<?>> properties = new HashSet<>();
    private Set<StatsWrapper<?>> statGoals;
    private boolean glow;
    private int hex;
    private ItemFlag[] flags;

    private Map<String, Function<PersistentDataContainer, Component>> variables = new HashMap<>();

    private CustomItem(String name, HoloItemsRevamp plugin) {
        this.key = new NamespacedKey(plugin, name);
    }

    public CustomItem(String name, Material material, HoloItemsRevamp plugin) {
        this(name, plugin);
        this.material = material;
    }

    public CustomItem(String name, Material material, Component displayName, HoloItemsRevamp plugin) {
        this(name, material, plugin);
        this.displayName = displayName;
    }

    public CustomItem(String name, Material material, Component displayName, List<Component> lore, HoloItemsRevamp plugin) {
        this(name, material, displayName, plugin);
        this.lore = lore;
    }

    @Override
    public final NamespacedKey getKey() {
        return key;
    }

    /**
     * Gets the internal name of this custom item
     * @return The internal name
     */
    public final String getInternalName() {
        return getKey().getKey();
    }

    /**
     * Create a new ItemStack for use. NOT for updating existing ones; see {@link #updateStack(ItemStack, Player)}
     * @param player The player
     * @return The ItemStack
     */
    public ItemStack buildStack(Player player) {
        ItemStack stack = new ItemStack(getMaterial());
        ItemMeta meta = stack.getItemMeta();

        //It's important to use the functions `getDisplayName()` and `getLore()` bellow
        //instead of the field names in case an object overrides them
        meta.displayName(replaceVariables(getDisplayName(), meta.getPersistentDataContainer()));

        if (meta instanceof LeatherArmorMeta) {
            ((LeatherArmorMeta) meta).setColor(Color.fromRGB(hex));
        }

        List<Component> loreList = new ArrayList<>();

        for (var line : getLore()) {
            loreList.add(replaceVariables(line, meta.getPersistentDataContainer()));
        }

        meta.lore(loreList);

        if (internalIntID != 0) meta.setCustomModelData(internalIntID); //Used for resource packs

        if (player != null) {
            if (properties.contains(Keys.OWNER)) {
                Keys.OWNER.set(meta.getPersistentDataContainer(), player.getUniqueId());
                Keys.OWNER_NAME.set(meta.getPersistentDataContainer(), player.getName());
            }
        }
        if (properties.contains(Keys.COOLDOWN)) {
            Keys.COOLDOWN.set(meta.getPersistentDataContainer(), 0L);
        }

        Keys.ITEM_ID.set(meta.getPersistentDataContainer(), getInternalName());

         //If the item shouldn't be stackable, add a random INTEGER to the NBT
        Keys.UNSTACKABLE.set(meta.getPersistentDataContainer(), !isStackable());

        if (glow) {
            stack.addUnsafeEnchantment(Enchantment.LUCK, 1);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        if (flags != null && flags.length > 0) meta.addItemFlags(flags);

        stack.setItemMeta(meta);

        return stack;
    }

    /**
     * Builds an ItemStack that should only be used for showing an item through an inventory or any other methods
     * that does not allow the player to use the item. This will add missing statistics to the lore.
     * @return An ItemStack with extra information.
     */
    public ItemStack buildGuiStack(OfflinePlayer player) {

        var itemStack = this.buildStack(null);
        var itemMeta = itemStack.getItemMeta();

        var lore = itemMeta.lore();

        var remainingStats = inspectStatGoals(player);
        if (remainingStats.isEmpty()) {
            return itemStack;
        }

        for (var statistic : remainingStats.keySet()) {
            //lore.add(ChatColor.BLUE + statistic.toString() + ChatColor.RESET + " | " + ChatColor.RED + remainingStats.get(statistic));
            lore.add(
                Component.text(statistic.toString(), NamedTextColor.BLUE)
                    .append(Component.text(" | "))
                    .append(Component.text(remainingStats.get(statistic), NamedTextColor.RED))
            );
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /**
     * Updates an existing itemstack with updated lore, name and variables
     * @param stack The itemstack
     * @param player The player holding it
     * @return The updated stack
     */
    public ItemStack updateStack(ItemStack stack, Player player) {
        ItemMeta originalMeta = stack.getItemMeta();
        ItemMeta meta = originalMeta;

        if (getMaterial() != stack.getType()) {
            if (originalMeta instanceof Damageable) {
                int damage = ((Damageable)originalMeta).getDamage();
                stack = buildStack(player); //Rebuild from scratch
                meta = stack.getItemMeta();
                if (meta instanceof Damageable) {
                    ((Damageable) meta).setDamage(damage);
                }
            }
        }
        if (properties.contains(Keys.OWNER)) {
            UUID uuid = Keys.OWNER.get(meta.getPersistentDataContainer());
            String ownerName;
            if (uuid != null) { //The owner can still be none if this is built using no player
                if (Bukkit.getPlayer(uuid) != null) { //If the player is online, use the new name
                    ownerName = Bukkit.getPlayer(uuid).getName();
                } else if (Keys.OWNER_NAME.has(meta.getPersistentDataContainer())) {
                    ownerName = Keys.OWNER_NAME.get(meta.getPersistentDataContainer());
                } else ownerName = player.getName(); //Failsafe is the new player's name
                Keys.OWNER_NAME.set(meta.getPersistentDataContainer(), ownerName);
            } else { //Owner is not defined but it should be
                if (player != null) { //Be sure we aren't gonna get an NPE
                    Keys.OWNER.set(meta.getPersistentDataContainer(), player.getUniqueId());
                    Keys.OWNER_NAME.set(meta.getPersistentDataContainer(), player.getName());
                }
            }
        }

        if (!properties.contains(Keys.RENAMABLE) || Keys.RENAMABLE.get(meta.getPersistentDataContainer()) == 0) {
            //It's important to use the functions `getDisplayName()` and `getLore()` bellow
            //instead of the field names in case an object overrides them
            meta.displayName(replaceVariables(getDisplayName(), meta.getPersistentDataContainer()));
        }


        List<Component> lore = new ArrayList<>();

        for (var line : getLore()) {
            lore.add(replaceVariables(line, meta.getPersistentDataContainer()));
        }

        meta.lore(lore);

        if (meta instanceof LeatherArmorMeta) {
            ((LeatherArmorMeta) meta).setColor(Color.fromRGB(hex));
        }

        if (internalIntID != 0) meta.setCustomModelData(internalIntID); //Used for resource packs

        if (glow) {
            stack.addUnsafeEnchantment(Enchantment.LUCK, 1);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        if (flags != null && flags.length > 0) meta.addItemFlags(flags);

        stack.setItemMeta(meta);

        return stack;
    }

    /**
     * Replaces the string provided with variables
     * @param component The component
     * @param dataHolder The data holder
     * @return The replaced string
     */
    public Component replaceVariables(Component component, PersistentDataContainer dataHolder) {
        var result = component;

        for (var entry : variables.entrySet()) {
            result = result.replaceText(
                TextReplacementConfig.builder().matchLiteral(entry.getKey())
                    .replacement(entry.getValue().apply(dataHolder))
                    .build()
            );
        }

        return result;
    }

    public void addVariable(String variable, Function<PersistentDataContainer, Component> function) {
        variables.put(variable, function);
    }

    /**
     * Unimplemented
     * @param speedPercentage
     * @return Itself
     */
    @Deprecated
    public CustomItem setToolSpeed(double speedPercentage) {
        //TODO
        return this;
    }

    /**
     * Set the display name
     * @param displayName The display name
     * @return Itself
     */
    public CustomItem setDisplayName(Component displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Get the custom display name
     * @return The display name
     */
    public Component getDisplayName() {
        return displayName;
    }

    /**
     * Get the material
     * @return The material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Set the material
     * @param material The material
     * @return Itself
     */
    public CustomItem setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Gets the cooldown of the item
     * @return The cooldown in milliseconds
     */
    public int getCooldown() {
        return cooldown;
    }

    /**
     * Sets the cooldown of the item
     * @param cooldown The cooldown of the item in millisecond
     */
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    /**
     * Applies a cooldown to an itemstack by storing the next time the item can be used.
     * @param item The itemStack
     * @return The itemStack with the cooldown.
     */
    public ItemStack applyCooldown(ItemStack item) {
        ItemMeta meta = item.hasItemMeta() ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta(item.getType());
        Keys.COOLDOWN.set(meta.getPersistentDataContainer(), Util.currentTimeTicks() + getCooldown());
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Checks if an item is on cooldown or not. If the plugin fails to get the meta then it returns false.
     * @param item The item
     * @return True if it's on cooldown. False otherwise.
     */
    public boolean checkCooldown(ItemStack item) {
        if (!item.hasItemMeta())
            return false;
        if (Keys.COOLDOWN.has(item.getItemMeta().getPersistentDataContainer())){
            return Keys.COOLDOWN
                .get(item.getItemMeta().getPersistentDataContainer()) >= Util.currentTimeTicks();
        }
        return false;
    }

    /**
     * Gets all the stat goals needed to unlock/use the item.
     * @return A set of StatsWrappers
     */
    public Set<StatsWrapper<?>> getStatGoals() {
        return statGoals;
    }

    /**
     * Sets the stat goals needed to unlock/use the item
     * @param statGoals A set of StatsWrappers
     */
    public void setStatGoals(Set<StatsWrapper<?>> statGoals) {
        this.statGoals = statGoals;
    }

    /**
     * Checks if a player passes all the stat goals
     * @param player The player to check
     * @return True if there is no stat goals, or the player passes all stat goals. False otherwise.
     */
    public boolean checkStatGoals(Player player) {
        if (getStatGoals() == null)
            return true;
        return getStatGoals().stream().allMatch(stat -> stat.checkPlayer(player));
    }

    /**
     * Returns a map that contains what stat goals the player hasn't reached. The map will not contain stat goals
     * that the player has passed.
     * @param player The player to check against.
     * @return A map, with the key being the statistic, and the value being the remaining goal.
     */
    @NonNull
    public Map<Statistic, Integer> inspectStatGoals(OfflinePlayer player) {
        Map<Statistic, Integer> remainingStats = new HashMap<>();
        if (getStatGoals() == null || getStatGoals().isEmpty())
            return remainingStats;

        getStatGoals().stream().filter(stat -> !stat.checkPlayer(player)).forEach(stat -> {
            remainingStats.put(stat.getStatistic(), stat.inspectPlayer(player));
        });
        return remainingStats;
    }

    /**
     * Make the item have an enchanted glow
     * @param glow True to glow
     * @return Itself
     */
    public CustomItem setEnchantedGlow(boolean glow) {
        this.glow = true;
        return this;
    }

    /**
     * Whether the item has an enchanted glow
     * @return The glow state
     */
    public boolean hasEnchantedGlow() {
        return this.glow;
    }

    /**
     * Get the lore
     * @return The lore
     */
    public List<Component> getLore() {
        return lore;
    }

    /**
     * Set the lore
     * @param lore The lore
     * @return Itself
     */
    public CustomItem setLore(List<Component> lore) {
        this.lore = lore;
        return this;
    }

    /**
     * Add a line to the lore
     * @param component The line to add
     * @return Itself
     */
    public CustomItem addLore(Component component) {
        if (this.lore == null) this.lore = new ArrayList<>();

        this.lore.add(component);

        return this;
    }

    public CustomItem setInternalID(int id) {
        this.internalIntID = id;
        return this;
    }

    public int getInternalID() {
        return internalIntID;
    }

    /**
     * Register this item to the registry
     * @return Itself
     */
    public CustomItem register() {
        CustomItemManager.register(this);
        return this;
    }

    /**
     * If the item is stackable
     * @return True if stackable
     */
    public boolean isStackable() {
        return stackable;
    }

    /**
     * Whether the item can be stacked
     * @param stackable Stackable
     * @return Itself
     */
    public CustomItem setStackable(boolean stackable) {
        this.stackable = stackable;
        return this;
    }

    /**
     * Get the properties of this item
     * @return The properties
     */
    public Set<Property<?>> getProperties() {
        return properties;
    }

    /**
     * Add a property to the item
     * @param property The property
     * @return Itself
     */
    public CustomItem addProperty(Property<?> property) {
        this.properties.add(property);
        return this;
    }

    @Override
    public String toString() {
        return "CustomItem{" +
                "name='" + getInternalName() + '\'' +
                ", textureID=" + internalIntID +
                ", material=" + material +
                ", displayName='" + displayName + "\'\u00A7r'" +
                ", stackable=" + stackable +
                ", properties=" + properties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomItem that = (CustomItem) o;

        return getKey().equals(that.getKey());
    }

    @Override
    public int hashCode() {
        return getKey().hashCode();
    }

    /**
     * Set the leather armor color of this item
     * @param hex The color
     * @return Itself
     */
    public CustomItem setLeatherColor(int hex) {
        this.hex = hex;
        return this;
    }

    /**
     * Get the leather armor color of this item
     * @return The color (0 for none)
     */
    public int getLeatherColor() {
        return this.hex;
    }

    /**
     * Get the flags applied to this item
     * @return The flags
     */
    public ItemFlag[] getFlags() {
        return flags;
    }

    /**
     * Set the flags of this item
     * @param flags
     * @return itself
     */
    public CustomItem setFlags(ItemFlag... flags) {
        this.flags = flags;
        return this;
    }
}
