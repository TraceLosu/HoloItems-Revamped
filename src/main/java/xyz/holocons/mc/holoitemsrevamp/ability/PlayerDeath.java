package xyz.holocons.mc.holoitemsrevamp.ability;

import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public interface PlayerDeath extends Ability {

    void run(PlayerDeathEvent event, ItemStack itemStack);
}
