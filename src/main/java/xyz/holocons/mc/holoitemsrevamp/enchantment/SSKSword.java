package xyz.holocons.mc.holoitemsrevamp.enchantment;

import com.destroystokyo.paper.MaterialTags;
import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;
import com.strangeone101.holoitemsapi.enchantment.EnchantmentAbility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import xyz.holocons.mc.holoitemsrevamp.HoloItemsRevamp;
import xyz.holocons.mc.holoitemsrevamp.Util;

import java.util.List;

public class SSKSword extends CustomEnchantment implements EnchantmentAbility {
    public SSKSword(HoloItemsRevamp plugin) {
        super(plugin, "ssk_sword");
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack itemStack) {
        return itemStack.getType() == Material.DIAMOND_SWORD;
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text()
            .color(NamedTextColor.GRAY)
            .decoration(TextDecoration.ITALIC, false)
            .append(Component.text("SSK Sword"))
            .build();
    }

    @Override
    public int getCostMultiplier() {
        return 12;
    }

    @Override
    public void onPlayerAttack(EntityDamageByEntityEvent event, ItemStack itemStack) {
        if(!(event.getEntity() instanceof LivingEntity target)) {
            return;
        }

        // This is what Klin used, and it seems to be equivalent to damage after-sharpness after-plugins before-prot4
        final var amountToHeal = event.getDamage();
        event.setDamage(-amountToHeal);

        // If there's fire aspect, add some sort of heal-over-time (since fire is DoT)
        // Code mostly copied from klin.
        int fireAspectLevel = itemStack.getEnchantmentLevel(FIRE_ASPECT);
        if(fireAspectLevel > 0) {
            target.addPotionEffects(List.of(
                new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 80*fireAspectLevel, 1),
                new PotionEffect(PotionEffectType.REGENERATION, 80*fireAspectLevel, 1),
                new PotionEffect(PotionEffectType.SPEED, 80*fireAspectLevel, 1)
            ));
        }

        // Add some cool particle effects? Code copied straight from klin,
        // unchanged besides "target" instead of "entity".
        Location loc = target.getLocation().add(0, target.getHeight()/2, 0);
        World world = target.getWorld();
        world.spawnParticle(Particle.HEART, loc, 3, 0.5, 0.5, 0.5);
        world.playSound(loc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1f, 1f);
    }
}
