package com.strangeone101.holoitemsapi.recipe;

import com.strangeone101.holoitemsapi.CustomItem;
import com.strangeone101.holoitemsapi.CustomItemManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;

/**
 * A recipe choice that uses a custom item
 */
public class CIRecipeChoice extends RecipeChoice.ExactChoice {

    public CIRecipeChoice(ItemStack... stacks) {
        super(stacks);
    }

    @Override
    public boolean test(ItemStack t) {
        for (ItemStack match : this.getChoices()) {
            if (CustomItemManager.isCustomItem(match)) {
                CustomItem ci = CustomItemManager.getCustomItem(match);
                if (ci != null && ci == CustomItemManager.getCustomItem(t) &&
                        (ci.getMaxDurability() == 0 || ci.getDurability(match) == ci.getDurability(t)))
                    return true;
            }
            else if (match.isSimilar(t))
                return true;
        }
        return false;
    }
}
