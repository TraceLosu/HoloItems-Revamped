package com.strangeone101.holoitemsapi.listener;

import com.strangeone101.holoitemsapi.CustomItemManager;
import com.strangeone101.holoitemsapi.recipe.NonConsumableChoice;
import com.strangeone101.holoitemsapi.recipe.RecipeBuilder;
import com.strangeone101.holoitemsapi.recipe.RecipeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
import org.bukkit.inventory.*;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;

import java.util.HashMap;
import java.util.Map;

public class CraftListener implements Listener {

    private final HoloItemsRevamp plugin;

    public CraftListener(HoloItemsRevamp plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onCraftItem(CraftItemEvent event) {
        ItemStack stack = event.getCurrentItem();
        if (CustomItemManager.isCustomItem(event.getCurrentItem()) && event.getWhoClicked() instanceof Player) {
            //Makes the output a fresh build of the item. Means it will be owned by that player
            event.setCurrentItem(CustomItemManager.getCustomItem(stack).buildStack((Player) event.getWhoClicked()));
        }

        if (!RecipeManager.isManagedRecipe(event.getRecipe())) {
            for (ItemStack ingredient : event.getInventory().getMatrix()) {
                if (CustomItemManager.isCustomItem(ingredient)) {
                    event.setCancelled(true);
                }
            }
        } else if (RecipeManager.isHiddenRecipe(event.getRecipe())) {
            RecipeBuilder.AdvancedRecipe recipe = RecipeManager.getAdvancedFromDummy(event.getRecipe());
            boolean notMatch = false;

            if (recipe instanceof RecipeBuilder.AdvancedShape) {
                RecipeBuilder.AdvancedShape advancedShape = (RecipeBuilder.AdvancedShape) recipe;
                CraftingInventory craftingInventory = event.getInventory();
                int size = craftingInventory.getSize() == 9 ? 3 : 2;
                int offset = 0;
                outter:
                for (int row = 0; row < size; row++) {
                    for (int col = 0; col < size; col++) {
                        int currIndex = row * size + col;
                        if (craftingInventory.getMatrix()[currIndex] != null) {
                            offset = currIndex - advancedShape.getFirstNotEmpty();
                            break outter;
                        }
                    }
                }

                String[] shape = advancedShape.getShape();
                ItemStack[] matrix = craftingInventory.getMatrix();
                outter:
                for (int row = 0; row < shape.length; row++) {
                    for (int col = 0; col < shape[row].length(); col++) {
                        int matrixNum = offset + (row * 3) + col;
                        if (shape[row].charAt(col) == ' ') {
                            if (matrix[matrixNum] != null) {
                                notMatch = true;
                                break outter;
                            }
                        } else {
                            ItemStack craftingStack = matrix[matrixNum];
                            if (!advancedShape.checkStack(shape[row].charAt(col), craftingStack)) {
                                notMatch = true;
                                break outter;
                            }
                        }
                    }
                }
            }

            if (!notMatch) {
                ItemStack updated = recipe.getCraftModifier().create(event.getInventory().getResult(),
                        recipe.getInputItems(event.getInventory()), recipe.buildContext(event.getInventory(), event.getClick()));

                event.getInventory().setResult(updated);
            } else {
                event.setCancelled(true);
            }
        } else if (RecipeManager.isAdvancedRecipe(event.getRecipe())) {
            RecipeBuilder.AdvancedRecipe advRecipe = RecipeManager.getAdvancedRecipe(event.getRecipe());

            ItemStack updated = advRecipe.getCraftModifier().create(event.getInventory().getResult(),
                    advRecipe.getInputItems(event.getInventory()), advRecipe.buildContext(event.getInventory(), event.getClick()));

            event.getInventory().setResult(updated);
            event.setCurrentItem(updated);
        }

        if (RecipeManager.hasNonConsumable(event.getRecipe())) {
            Map<Integer, ItemStack> slots = new HashMap<>();
            for (int slot = 0; slot < event.getInventory().getSize(); slot++) {
                ItemStack slotItem = event.getInventory().getItem(slot);

                if (event.getRecipe() instanceof ShapedRecipe) {
                    for (RecipeChoice choice : ((ShapedRecipe) event.getRecipe()).getChoiceMap().values()) {
                        if (choice instanceof NonConsumableChoice && choice.test(slotItem)) {
                            slots.put(slot, slotItem.clone());
                            if (event.isShiftClick()) { //If they shift click,
                                slotItem.setAmount(64); //Allow it to craft as many as possible
                                event.getInventory().setItem(slot, slotItem);
                            }
                        }
                    }
                } else if (event.getRecipe() instanceof ShapelessRecipe) {
                    for (RecipeChoice choice : ((ShapelessRecipe) event.getRecipe()).getChoiceList()) {
                        if (choice instanceof NonConsumableChoice && choice.test(slotItem)) {
                            slots.put(slot, slotItem.clone());
                            if (event.isShiftClick()) { //If they shift click,
                                slotItem.setAmount(64); //Allow it to craft as many as possible
                                event.getInventory().setItem(slot, slotItem);
                            }
                        }
                    }
                }
            }

            //1 tick later, restore the items that were removed
            if (slots.size() > 0) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (int slot : slots.keySet()) {
                            event.getInventory().setItem(slot, slots.get(slot));
                        }
                    }
                }.runTaskLater(plugin, 1L);
            }
        }
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        if (!RecipeManager.isManagedRecipe(event.getRecipe())) {
            for (ItemStack ingredient : event.getInventory().getMatrix()) {
                if (CustomItemManager.isCustomItem(ingredient)) {
                    event.getInventory().setResult(null); //Stops recipes using our custom items
                }
            }
        } else if (RecipeManager.isHiddenRecipe(event.getRecipe())) {
            RecipeBuilder.AdvancedRecipe recipe = RecipeManager.getAdvancedFromDummy(event.getRecipe());
            boolean notMatch = false;

            if (recipe instanceof RecipeBuilder.AdvancedShape) {
                RecipeBuilder.AdvancedShape advancedShape = (RecipeBuilder.AdvancedShape) recipe;
                CraftingInventory craftingInventory = event.getInventory();
                int size = craftingInventory.getSize() == 9 ? 3 : 2;
                int offset = 0;
                outter:
                for (int row = 0; row < size; row++) {
                    for (int col = 0; col < size; col++) {
                        int currIndex = row * size + col;
                        if (craftingInventory.getMatrix()[currIndex] != null) {
                            offset = currIndex - advancedShape.getFirstNotEmpty();
                            break outter;
                        }
                    }
                }

                String[] shape = advancedShape.getShape();
                ItemStack[] matrix = craftingInventory.getMatrix();
                outter:
                for (int row = 0; row < shape.length; row++) {
                    for (int col = 0; col < shape[row].length(); col++) {
                        int matrixNum = offset + (row * 3) + col;
                        if (shape[row].charAt(col) == ' ') {
                            if (matrix[matrixNum] != null) {
                                notMatch = true;
                                break outter;
                            }
                        } else {
                            ItemStack craftingStack = matrix[matrixNum];
                            if (!advancedShape.checkStack(shape[row].charAt(col), craftingStack)) {
                                notMatch = true;
                                break outter;
                            }
                        }
                    }
                }
            }

            if (!notMatch) {
                ItemStack updated = recipe.getPreviewModifier().create(event.getInventory().getResult(),
                        recipe.getInputItems(event.getInventory()), recipe.buildContext(event.getInventory(), null));

                event.getInventory().setResult(updated);
            } else {
                event.getInventory().setResult(null);
            }
        } else if (RecipeManager.isAdvancedRecipe(event.getRecipe())) {
            RecipeBuilder.AdvancedRecipe advRecipe = RecipeManager.getAdvancedRecipe(event.getRecipe());

            ItemStack updated = advRecipe.getPreviewModifier().create(event.getInventory().getResult(),
                    advRecipe.getInputItems(event.getInventory()), advRecipe.buildContext(event.getInventory(), null));

            event.getInventory().setResult(updated);
        }
    }

    @EventHandler
    public void onPlayerRecipeDiscover(PlayerRecipeDiscoverEvent event) {
        if (RecipeManager.isHiddenRecipe(event.getRecipe())) {
            event.setCancelled(true);
        }
    }
}
