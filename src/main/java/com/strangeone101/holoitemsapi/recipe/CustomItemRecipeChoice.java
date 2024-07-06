package com.strangeone101.holoitemsapi.recipe;

import java.util.stream.Stream;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;

import com.strangeone101.holoitemsapi.item.CustomItem;
import com.strangeone101.holoitemsapi.item.CustomItemManager;

/**
 * A recipe choice that uses a custom item
 */
public class CustomItemRecipeChoice extends RecipeChoice.ExactChoice {

    public CustomItemRecipeChoice(String... ids) {
        super(Stream.of(ids).map(id -> CustomItemManager.getCustomItem(id).buildStack(null)).toList());
    }

    @Override
    public boolean test(ItemStack t) {
        for (ItemStack match : this.getChoices()) {
            if (CustomItemManager.isCustomItem(match)) {
                CustomItem ci = CustomItemManager.getCustomItem(match);
                if (ci != null && ci == CustomItemManager.getCustomItem(t)) {
                    return true;
                }
            } else if (match.isSimilar(t)) {
                return true;
            }
        }
        return false;
    }
}
