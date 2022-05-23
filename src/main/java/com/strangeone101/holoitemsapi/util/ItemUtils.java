package com.strangeone101.holoitemsapi.util;

import com.strangeone101.holoitemsapi.CustomItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class ItemUtils {

    /**
     * Sets attributes on an item
     * @param number The amount to use
     * @param attribute The attribute
     * @param operation The operation
     * @param stack The itemstack
     * @param ci The custom item
     */
    public static void setAttribute(double number, Attribute attribute, AttributeModifier.Operation operation, ItemStack stack, CustomItem ci) {
        Random random = new Random(cantorFunction(ci.getInternalID(), cantorFunction(attribute.ordinal(), operation.ordinal())));

        try {
            AttributeModifier mod = new AttributeModifier(new UUID(random.nextLong(), random.nextLong()), attribute.name(), number, operation, getSlotForItem(stack.getType()));
            ItemMeta meta = stack.getItemMeta();
            meta.addAttributeModifier(attribute, mod);
            stack.setItemMeta(meta);
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Gets what Equipment slot this item should be used for
     * @param material The material
     * @return The EquipmentSlot
     */
    public static EquipmentSlot getSlotForItem(Material material) {
        switch (material) {
            case SHIELD:
                return EquipmentSlot.OFF_HAND;
            case LEATHER_BOOTS:
            case IRON_BOOTS:
            case GOLDEN_BOOTS:
            case CHAINMAIL_BOOTS:
            case DIAMOND_BOOTS:
            case NETHERITE_BOOTS:
                return EquipmentSlot.FEET;
            case LEATHER_LEGGINGS:
            case IRON_LEGGINGS:
            case CHAINMAIL_LEGGINGS:
            case GOLDEN_LEGGINGS:
            case DIAMOND_LEGGINGS:
            case NETHERITE_LEGGINGS:
                return EquipmentSlot.LEGS;
            case LEATHER_CHESTPLATE:
            case IRON_CHESTPLATE:
            case CHAINMAIL_CHESTPLATE:
            case GOLDEN_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case NETHERITE_CHESTPLATE:
            case ELYTRA:
                return EquipmentSlot.CHEST;
            case LEATHER_HELMET:
            case IRON_HELMET:
            case CHAINMAIL_HELMET:
            case GOLDEN_HELMET:
            case DIAMOND_HELMET:
            case NETHERITE_HELMET:
            case TURTLE_HELMET:
            case CARVED_PUMPKIN:
                return EquipmentSlot.HEAD;
            default:
                return EquipmentSlot.HAND;
        }
    }

    /**
     * Is this material meat?
     * @param material The material?
     * @return True if it's meat
     */
    public static boolean isMeat(Material material) {
        switch (material) {
            case BEEF:          case COOKED_BEEF:
            case MUTTON:        case COOKED_MUTTON:
            case COD:           case COOKED_COD:
            case SALMON:        case COOKED_SALMON:
            case RABBIT:        case COOKED_RABBIT:
            case CHICKEN:       case COOKED_CHICKEN:
            case PORKCHOP:      case COOKED_PORKCHOP:
                return true;
            default:
                return false;
        }
    }

    /**
     * Is this material fish?
     * @param material The material?
     * @return True if it's fish
     */
    public static boolean isFish(Material material) {
        switch (material) {
            case COD:               case COOKED_COD:
            case SALMON:            case COOKED_SALMON:
            case TROPICAL_FISH:     case PUFFERFISH:
                return true;
            default:
                return false;
        }
    }

    /**
     * Is this material a dye?
     * @param material The material?
     * @return True if it's a dye
     */
    public static boolean isDye(Material material) {
        switch (material) {
            case WHITE_DYE:         case BLACK_DYE:
            case BLUE_DYE:          case BROWN_DYE:
            case CYAN_DYE:          case GRAY_DYE:
            case GREEN_DYE:         case LIGHT_BLUE_DYE:
            case LIGHT_GRAY_DYE:    case LIME_DYE:
            case MAGENTA_DYE:       case ORANGE_DYE:
            case PINK_DYE:          case PURPLE_DYE:
            case RED_DYE:           case YELLOW_DYE:
                return true;
            default:
                return false;
        }
    }

    /**
     * Is this material leather armor?
     * @param material The material
     * @return True if it's leather
     */
    public static boolean isLeatherArmor(Material material) {
        switch (material) {
            case LEATHER_BOOTS:
            case LEATHER_CHESTPLATE:
            case LEATHER_LEGGINGS:
            case LEATHER_HELMET:
                return true;
            default:
                return false;
        }
    }

    /**
     * Create one unique number from two different number
     * Note that cantorFunction(1,0) is different from cantorFunction(0,1)
     * @param x The first number
     * @param y The second number
     * @return A unique number
     */
    public static int cantorFunction(int x, int y) {
        return (((x * x) + (3 * x) + (2 * x * y) + y + (y * y)) / 2);
    }

    /**
     * Get a fancy string that represents the durability % left
     * @param durability The durability
     * @param maxDurability The max durability
     * @return The string
     */
    public static Component getDurabilityString(int durability, int maxDurability) {
        if (maxDurability == 0) return Component.empty(); //No durability
        double percentage = ((double) durability) / ((double) maxDurability);
        double bigPercentage = percentage * 100;
        var color = NamedTextColor.DARK_RED;
        if (bigPercentage >= 90) color = NamedTextColor.DARK_GREEN;
        else if (bigPercentage >= 60) color = NamedTextColor.GREEN;
        else if (bigPercentage >= 40) color = NamedTextColor.YELLOW;
        else if (bigPercentage > 25) color = NamedTextColor.GOLD;
        else if (bigPercentage > 5) color = NamedTextColor.RED; //5% or lower will be dark red since that is the default

        var template = "■■■■■■■■■■■■"; //The bar that shows the item health
        int percentInt = (int) (template.length() * percentage);

        var coloredPart = Component.text(template.substring(0, percentInt), color);
        var greyPart = Component.text(template.substring(percentInt), NamedTextColor.GRAY);

        var complete = coloredPart.append(greyPart);
        DecimalFormat dc = new DecimalFormat();
        dc.setMaximumFractionDigits(2);
        dc.setMaximumIntegerDigits(3);
        dc.setMinimumFractionDigits(0);
        dc.setMinimumIntegerDigits(1);

        complete = Component.text(dc.format(bigPercentage) + "%", color); //Format of '100% BARBARBARBAR'

        return complete;

    }
}
