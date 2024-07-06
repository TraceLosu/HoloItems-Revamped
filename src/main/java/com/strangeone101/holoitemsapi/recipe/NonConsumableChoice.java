package com.strangeone101.holoitemsapi.recipe;

/**
 * A recipe choice item that will not be consumed in recipes. Like how
 * milk buckets do not consume the bucket when used in crafting
 */
public class NonConsumableChoice extends CustomItemRecipeChoice {

    public NonConsumableChoice(String... ids) {
        super(ids);
    }

}
