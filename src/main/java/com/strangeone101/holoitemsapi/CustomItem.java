package com.strangeone101.holoitemsapi;

import com.strangeone101.holoitemsapi.util.ItemUtils;
import com.strangeone101.holoitemsapi.util.ReflectionUtils;
import com.strangeone101.holoitemsapi.util.StatsWrapper;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * A class for creating custom items. Be sure to call {@link #register()} after creating it
 * to properly register it
 */
public class CustomItem implements Keyed {

    private NamespacedKey key;
    private int internalIntID;

    private Material material;
    private String displayName;
    private List<String> lore = new ArrayList<>();
    private boolean jsonLore = false;
    private int maxDurability = 0;
    private int cooldown = 0;
    private boolean stackable = true;
    private Set<Property<?>> properties = new HashSet<>();
    private Set<StatsWrapper<?>> statGoals;
    private String extraData;
    private boolean ench;
    private int hex;
    private ItemFlag[] flags;
    private BiConsumer<ItemStack, ItemMeta> onBuild;
    private BiConsumer<ItemStack, ItemMeta> onUpdate;

    private Map<Attribute, Map<AttributeModifier.Operation, Double>> attributes = new HashMap<>();
    private Map<String, Function<PersistentDataContainer, String>> variables = new HashMap<>();

    private CustomItem(String name) {
        this.key = new NamespacedKey(HoloItemsAPI.getPlugin(), name);
    }

    public CustomItem(String name, Material material) {
        this(name);
        this.material = material;
    }

    public CustomItem(String name, Material material, String displayName) {
        this(name, material);
        this.displayName = displayName;
    }

    public CustomItem(String name, Material material, String displayName, List<String> lore) {
        this(name, material, displayName);
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
        meta.setDisplayName(replaceVariables(getDisplayName(), meta.getPersistentDataContainer()));

        if (meta instanceof LeatherArmorMeta) {
            ((LeatherArmorMeta) meta).setColor(Color.fromRGB(hex));
        }
        List<String> lore = new ArrayList<>();

        for (String line : getLore()) {
            lore.add(replaceVariables(line, meta.getPersistentDataContainer()));
        }
        if (!this.jsonLore) {
            meta.setLore(lore);
        }
        if (internalIntID != 0) meta.setCustomModelData(internalIntID); //Used for resource packs

        if (player != null) {
            if (properties.contains(Properties.OWNER)) {
                Properties.OWNER.set(meta.getPersistentDataContainer(), player.getUniqueId());
                Properties.OWNER_NAME.set(meta.getPersistentDataContainer(), player.getName());
            }
        }
        if (properties.contains(Properties.COOLDOWN)) {
            Properties.COOLDOWN.set(meta.getPersistentDataContainer(), 0L);
        }

        Properties.ITEM_ID.set(meta.getPersistentDataContainer(), getInternalName());
        //meta.getPersistentDataContainer().set(HoloItemsPlugin.getKeys().CUSTOM_ITEM_ID, PersistentDataType.STRING, getInternalName());
        if (getMaxDurability() > 0) {
            meta.getPersistentDataContainer().set(HoloItemsAPI.getKeys().CUSTOM_ITEM_DURABILITY, PersistentDataType.INTEGER, 0);
        }

         //If the item shouldn't be stackable, add a random INTEGER to the NBT
        Properties.UNSTACKABLE.set(meta.getPersistentDataContainer(), !isStackable());

        if (ench) {
            stack.addUnsafeEnchantment(Enchantment.LURE, 1);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        if (flags != null && flags.length > 0) meta.addItemFlags(flags);

        stack.setItemMeta(meta);

        //Add all attributes to the item
        for (Attribute attr : getAttributes().keySet()) {
            for (AttributeModifier.Operation operation: getAttributes().get(attr).keySet()) {
                ItemUtils.setAttribute(getAttributes().get(attr).get(operation), attr, operation, stack, this);
            }
        }

        if (this.jsonLore) {
            ReflectionUtils.setTrueLore(stack, lore);
        }

        if (onBuild != null) {
            meta = stack.getItemMeta();
            onBuild.accept(stack, meta);
        }

        return stack;
    }

    /**
     * Builds an ItemStack that should only be used for showing an item through an inventory or any other methods
     * that does not allow the player to use the item. This will add missing statistics to the lore.
     * @return An ItemStack with extra information.
     */
    public ItemStack buildGuiStack(OfflinePlayer player) {
        var itemStack = this.buildStack(null);
        var itemStackMeta = itemStack.getItemMeta();
        List<String> lore;
        if (itemStackMeta.hasLore()) {
            lore = itemStackMeta.getLore();
        } else {
            lore = new ArrayList<>();
        }

        var remainingStats = inspectStatGoals(player);
        if (remainingStats.isEmpty()) {
            return itemStack;
        }

        for (var statistic : remainingStats.keySet()) {
            lore.add(ChatColor.BLUE + statistic.toString() + ChatColor.RESET + " | " + ChatColor.RED + remainingStats.get(statistic));
        }

        itemStack.setItemMeta(itemStackMeta);
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
        if (properties.contains(Properties.OWNER)) {
            UUID uuid = Properties.OWNER.get(meta.getPersistentDataContainer());
            String ownerName;
            if (uuid != null) { //The owner can still be none if this is built using no player
                if (Bukkit.getPlayer(uuid) != null) { //If the player is online, use the new name
                    ownerName = Bukkit.getPlayer(uuid).getName();
                } else if (Properties.OWNER_NAME.has(meta.getPersistentDataContainer())) {
                    ownerName = Properties.OWNER_NAME.get(meta.getPersistentDataContainer());
                } else ownerName = player.getName(); //Failsafe is the new player's name
                Properties.OWNER_NAME.set(meta.getPersistentDataContainer(), ownerName);
            } else { //Owner is not defined but it should be
                if (player != null) { //Be sure we aren't gonna get an NPE
                    Properties.OWNER.set(meta.getPersistentDataContainer(), player.getUniqueId());
                    Properties.OWNER_NAME.set(meta.getPersistentDataContainer(), player.getName());
                }
            }
        }

        if (!properties.contains(Properties.RENAMABLE) || Properties.RENAMABLE.get(meta.getPersistentDataContainer()) == 0) {
            //It's important to use the functions `getDisplayName()` and `getLore()` bellow
            //instead of the field names in case an object overrides them
            meta.setDisplayName(replaceVariables(getDisplayName(), meta.getPersistentDataContainer()));
        }


        List<String> lore = new ArrayList<>();

        for (String line : getLore()) {
            lore.add(replaceVariables(line, meta.getPersistentDataContainer()));
        }
        if (!this.jsonLore) {
            meta.setLore(lore);
        }
        if (meta instanceof LeatherArmorMeta) {
            ((LeatherArmorMeta) meta).setColor(Color.fromRGB(hex));
        }
        if (internalIntID != 0) meta.setCustomModelData(internalIntID); //Used for resource packs

        if (ench) {
            stack.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        if (flags != null && flags.length > 0) meta.addItemFlags(flags);

        stack.setItemMeta(meta);

        //Add all attributes to the item
        for (Attribute attr : getAttributes().keySet()) {
            for (AttributeModifier.Operation operation: getAttributes().get(attr).keySet()) {
                ItemUtils.setAttribute(getAttributes().get(attr).get(operation), attr, operation, stack, this);
            }
        }

        if (this.jsonLore) {
            ReflectionUtils.setTrueLore(stack, lore);
        }

        if (onUpdate != null) {
            meta = stack.getItemMeta();
            onUpdate.accept(stack, meta);
        }

        return stack;
    }

    /**
     * Damages the item's durability
     * @param stack The itemstack
     * @param amount The amount to damage it
     * @param player The player damageing it
     */
    public void damageItem(ItemStack stack, int amount, Player player) {
        if (getMaxDurability() > 0 && player.getGameMode() != GameMode.CREATIVE) {
            ItemMeta meta = stack.getItemMeta();
            int damage = meta.getPersistentDataContainer().getOrDefault(HoloItemsAPI.getKeys().CUSTOM_ITEM_DURABILITY, PersistentDataType.INTEGER, 0);
            damage += amount;

            if (damage >= getMaxDurability()) {
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
                Location inFront = player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().clone().multiply(0.3));
                player.getWorld().spawnParticle(Particle.ITEM_CRACK, inFront, 16, 0.25F, 0.25F, 0.25F, 0.05F, stack);
                stack.setType(Material.AIR);
                return;
            }

            setDurability(stack, damage);
            updateStack(stack, player);
        }
    }

    /**
     * Get a fancy string that represents the durability % left
     * @param durability The durability
     * @param maxDurability The max durability
     * @return The string
     */
    public static String getDurabilityString(int durability, int maxDurability) {
        if (maxDurability == 0) return ""; //No durability
        double percentage = ((double) durability) / ((double) maxDurability);
        double bigPercentage = percentage * 100;
        ChatColor color = ChatColor.DARK_RED;
        if (bigPercentage >= 90) color = ChatColor.DARK_GREEN;
        else if (bigPercentage >= 60) color = ChatColor.GREEN;
        else if (bigPercentage >= 40) color = ChatColor.YELLOW;
        else if (bigPercentage > 25) color = ChatColor.GOLD;
        else if (bigPercentage > 5) color = ChatColor.RED; //5% or lower will be dark red since that is the default

        String template = "■■■■■■■■■■■■"; //The bar that shows the item health
        int percentInt = (int) (template.length() * percentage);

        String coloredPart = color + template.substring(0, percentInt);
        String greyPart = ChatColor.GRAY + template.substring(percentInt);

        String complete = coloredPart + greyPart;
        DecimalFormat dc = new DecimalFormat();
        dc.setMaximumFractionDigits(2);
        dc.setMaximumIntegerDigits(3);
        dc.setMinimumFractionDigits(0);
        dc.setMinimumIntegerDigits(1);

        complete = color + dc.format(bigPercentage) + "% " + complete; //Format of '100% BARBARBARBAR'

        return complete;

    }

    /**
     * Get the durability on this custom item
     * @param stack The custom item stack
     * @return The durability
     */
    public int getDurability(ItemStack stack) {
        if (getMaxDurability() > 0) {
            ItemMeta meta = stack.getItemMeta();
            return meta.getPersistentDataContainer().getOrDefault(HoloItemsAPI.getKeys().CUSTOM_ITEM_DURABILITY, PersistentDataType.INTEGER, 0);
        }
        return 0;
    }

    /**
     * Set the durability of this custom item
     * @param stack The custom item stack
     * @param durability The durability
     */
    public void setDurability(ItemStack stack, int durability) {
        if (getMaxDurability() > 0) {
            if (durability <= 0) {
                stack.setType(Material.AIR);
                return;
            }
            ItemMeta meta = stack.getItemMeta();
            meta.getPersistentDataContainer().set(HoloItemsAPI.getKeys().CUSTOM_ITEM_DURABILITY, PersistentDataType.INTEGER, durability);

            if (meta instanceof Damageable) {
                ((Damageable) meta).setDamage((int)(((double)durability / (double)getMaxDurability()) * stack.getType().getMaxDurability()));
            }

            stack.setItemMeta(meta); //Update item
        }
        return;
    }

    /**
     * Set the custom skin of the head
     * @param skin The skin
     * @return Itself
     */
    public CustomItem setHeadSkin(String skin) {
        if (material != Material.PLAYER_HEAD && material != Material.PLAYER_WALL_HEAD) {
            this.extraData = skin;
        }

        return this;
    }

    /**
     * Replaces the string provided with variables
     * @param string The string
     * @param dataHolder The data holder
     * @return The replaced string
     */
    public String replaceVariables(String string, PersistentDataContainer dataHolder) {
        String s = string;
        if (getMaxDurability() > 0) {
            int damage = dataHolder.getOrDefault(HoloItemsAPI.getKeys().CUSTOM_ITEM_DURABILITY, PersistentDataType.INTEGER, 0);
            damage = getMaxDurability() - damage;
            s = s.replace("{durability}", getDurabilityString(damage, getMaxDurability()));
        }
        for (String variable : variables.keySet()) {
            String endResult = variables.get(variable).apply(dataHolder);

            if (endResult == null) continue; //Variable not ready for use yet

            s = s.replace("{" + variable + "}", endResult);
        }
        return s;
    }

    public void addVariable(String variable, Function<PersistentDataContainer, String> function) {
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
    public CustomItem setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Get the custom display name
     * @return The display name
     */
    public String getDisplayName() {
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
        Properties.COOLDOWN.set(meta.getPersistentDataContainer(), System.currentTimeMillis() + getCooldown());
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
        if (Properties.COOLDOWN.has(item.getItemMeta().getPersistentDataContainer())){
            return Properties.COOLDOWN.get(item.getItemMeta().getPersistentDataContainer()) >= System.currentTimeMillis();
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
        this.ench = true;
        return this;
    }

    /**
     * Whether the item has an enchanted glow
     * @return The glow state
     */
    public boolean hasEnchantedGlow() {
        return this.ench;
    }

    /**
     * Get the lore
     * @return The lore
     */
    public List<String> getLore() {
        return lore;
    }

    /**
     * Set the lore
     * @param lore The lore
     * @return Itself
     */
    public CustomItem setLore(List<String> lore) {
        this.lore = lore;
        this.jsonLore = false;
        return this;
    }

    /**
     * Add a line to the lore
     * @param string The line to add
     * @return Itself
     */
    public CustomItem addLore(String string) {
        if (this.lore == null) this.lore = new ArrayList<>();

        if (this.jsonLore) {
            //Don't bother adding white and non italics to nothing
            if (string.length() == 0) lore.add(ComponentSerializer.toString(new TextComponent()));
            else {
                //Append white and non italics to the front so it doesn't appear in italics
                BaseComponent comp = new ComponentBuilder().append("")
                        .color(net.md_5.bungee.api.ChatColor.WHITE).italic(false).getCurrentComponent();
                comp.setExtra(Arrays.asList(TextComponent.fromLegacyText(string)));
                lore.add(ComponentSerializer.toString(comp));
            }

        } else {
            lore.add(string);
        }

        return this;
    }

    public CustomItem addLore(BaseComponent component) {
        if (this.lore == null) this.lore = new ArrayList<>();
        if (!this.jsonLore) {
            this.jsonLore = true;
            List<String> jsonList = new ArrayList<>();

            for (String s : this.lore) {
                //Don't bother adding white and non italics to nothing
                if (s.length() == 0) jsonList.add(ComponentSerializer.toString(new TextComponent()));
                else {
                    //Append white and non italics to the front so it doesn't appear in italics
                    BaseComponent comp = new ComponentBuilder().append("")
                            .color(net.md_5.bungee.api.ChatColor.WHITE).italic(false).getCurrentComponent();
                    comp.setExtra(Arrays.asList(TextComponent.fromLegacyText(s)));
                    jsonList.add(ComponentSerializer.toString(comp));
                }
            }
            this.lore = jsonList;
        }

        BaseComponent baseComp = new ComponentBuilder().append("")
                .color(net.md_5.bungee.api.ChatColor.WHITE).italic(false).getCurrentComponent();
        baseComp.addExtra(component);
        this.lore.add(ComponentSerializer.toString(baseComp));
        return this;
    }

    /**
     * Get the max durability of the item
     * @return The durability
     */
    public int getMaxDurability() {
        return maxDurability;
    }

    /**
     * Set the max durability of the item
     * @param maxDurability The durability
     * @return Itself
     */
    public CustomItem setMaxDurability(int maxDurability) {
        this.maxDurability = maxDurability;
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
        CustomItemRegistry.register(this);
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
                ", maxDurability=" + maxDurability +
                ", stackable=" + stackable +
                ", properties=" + properties +
                ", extraData='" + extraData + '\'' +
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

    public CustomItem setAttribute(Attribute attribute, double amount, boolean percentage) {
        AttributeModifier.Operation operation = AttributeModifier.Operation.ADD_NUMBER;
        if (percentage) {
            operation = AttributeModifier.Operation.ADD_SCALAR;
        }
        HashMap<AttributeModifier.Operation, Double> map = new HashMap<>();
        map.put(operation, amount);
        getAttributes().put(attribute, map);

        return this;
    }

    public CustomItem setArmor(double armor, boolean percentage) {
        return setAttribute(Attribute.GENERIC_ARMOR, armor, percentage);
    }

    public CustomItem setArmor(double armor) {
        return setArmor(armor, false);
    }

    public CustomItem setArmorPercentage(double armorPercentage) {
        return setArmor(armorPercentage, true);
    }

    public CustomItem setArmorToughness(double armorToughness, boolean percentage) {
        return setAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS, armorToughness, percentage);
    }

    public CustomItem setArmorToughness(double armorToughness) {
        return setArmorToughness(armorToughness, false);
    }

    public CustomItem setArmorToughnessPercentage(double armorToughnessPercentage) {
        return setArmorToughness(armorToughnessPercentage, true);
    }

    public CustomItem setDamage(double damage, boolean percentage) {
        return setAttribute(Attribute.GENERIC_ATTACK_DAMAGE, damage, percentage);
    }

    public CustomItem setDamage(double damage) {
        return setDamage(damage, false);
    }

    public CustomItem setDamagePercentage(double damagePercentage) {
        return setDamage(damagePercentage, true);
    }

    public CustomItem setAttackKnockback(double knockback, boolean percentage) {
        return setAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK, knockback, percentage);
    }

    public CustomItem setAttackKnockback(double knockback) {
        return setAttackKnockback(knockback, false);
    }

    public CustomItem setAttackKnockbackPercentage(double knockbackPercentage) {
        return setAttackKnockback(knockbackPercentage, true);
    }

    public CustomItem setAttackSpeed(double speed, boolean percentage) {
        return setAttribute(Attribute.GENERIC_ATTACK_SPEED, speed, percentage);
    }

    public CustomItem setAttackSpeed(double speed) {
        return setAttackSpeed(speed, false);
    }

    public CustomItem setAttackSpeedPercentage(double speedPercentage) {
        return setAttackSpeed(speedPercentage, true);
    }

    public CustomItem setKnockbackResistance(double resistance, boolean percentage) {
        return setAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE, resistance, percentage);
    }

    public CustomItem setKnockbackResistance(double resistance) {
        return setKnockbackResistance(resistance, false);
    }

    public CustomItem setKnockbackResistancePercentage(double resistancePercentage) {
        return setKnockbackResistance(resistancePercentage, true);
    }

    public CustomItem setLuck(double luck, boolean percentage) {
        return setAttribute(Attribute.GENERIC_LUCK, luck, percentage);
    }

    public CustomItem setLuck(double luck) {
        return setLuck(luck, false);
    }

    public CustomItem setLuckPercentage(double luckPercentage) {
        return setLuck(luckPercentage, true);
    }

    public CustomItem setHealth(double health, boolean percentage) {
        return setAttribute(Attribute.GENERIC_MAX_HEALTH, health, percentage);
    }

    public CustomItem setHealth(int health) {
        return setHealth(health, false);
    }

    public CustomItem setHealthPercentage(double healthPercentage) {
        return setHealth(healthPercentage, true);
    }

    public CustomItem setSpeed(double speed, boolean percentage) {
        return setAttribute(Attribute.GENERIC_MOVEMENT_SPEED, speed, percentage);
    }

    public CustomItem setSpeed(double speed) {
        return setSpeed(speed, false);
    }

    public CustomItem setSpeedPercentage(double speedPercentage) {
        return setSpeed(speedPercentage, true);
    }

    public Map<Attribute, Map<AttributeModifier.Operation, Double>> getAttributes() {
        return attributes;
    }

    /**
     * Run some code when this item is built. Used in case you don't want to create
     * a new class just to change something about the item.
     *
     * {code}item.onBuild((itemstack, meta) -> itemstack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10)){code}
     * @param consumer The code to run
     * @return Itself
     */
    public CustomItem onBuild(BiConsumer<ItemStack, ItemMeta> consumer) {
        this.onBuild = consumer;
        return this;
    }

    /**
     * Run some code when this item is updated (picked up or regenerated). Used in case you don't want to create
     * a new class just to change something about the item.
     *
     * {code}item.onUpdate((itemstack, meta) -> itemstack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10)){code}
     * @param consumer The code to run
     * @return Itself
     */
    public CustomItem onUpdate(BiConsumer<ItemStack, ItemMeta> consumer) {
        this.onUpdate = consumer;
        return this;
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
