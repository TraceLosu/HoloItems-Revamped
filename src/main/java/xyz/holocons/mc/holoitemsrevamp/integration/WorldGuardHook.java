package xyz.holocons.mc.holoitemsrevamp.integration;

import java.util.Map;

import org.bukkit.Location;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.strangeone101.holoitemsapi.enchantment.CustomEnchantment;

import xyz.holocons.mc.holoitemsrevamp.enchantment.Magnet;
import xyz.holocons.mc.holoitemsrevamp.enchantment.Memento;
import xyz.holocons.mc.holoitemsrevamp.enchantment.TideRider;

public interface WorldGuardHook extends Hook {

    default boolean canUseEnchantment(Location location, Class<? extends CustomEnchantment> enchantmentCls) {
        return true;
    }

    public class Stub implements WorldGuardHook {
    }

    public class Integration implements WorldGuardHook {

        public static final Map<Class<? extends CustomEnchantment>, Flag<?>> ENCHANTMENT_FLAGS = Map.ofEntries(
                Map.entry(Magnet.class, new StateFlag("holoitems-magnet", true)),
                Map.entry(Memento.class, new StateFlag("holoitems-memento", true)),
                Map.entry(TideRider.class, new StateFlag("holoitems-tide-rider", true)));

        private RegionContainer regionContainer;

        @Override
        public void onLoad() {
            WorldGuard.getInstance().getFlagRegistry().registerAll(ENCHANTMENT_FLAGS.values());
        }

        @Override
        public void onEnable() {
            this.regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        }

        @Override
        public boolean canUseEnchantment(Location location, Class<? extends CustomEnchantment> enchantmentCls) {
            final var value = regionContainer.createQuery()
                    .queryValue(BukkitAdapter.adapt(location), null, ENCHANTMENT_FLAGS.get(enchantmentCls));
            return value != StateFlag.State.DENY;
        }
    }
}
