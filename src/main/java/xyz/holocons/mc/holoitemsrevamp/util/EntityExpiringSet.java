package xyz.holocons.mc.holoitemsrevamp.util;

import java.util.UUID;

import org.bukkit.entity.Entity;

public class EntityExpiringSet extends ExpiringSet<UUID, Entity> {

    public static class ConstantTicksToLiveExpirationPolicy<K> implements ExpirationPolicy<K> {

        private final long ticksToLive;

        public ConstantTicksToLiveExpirationPolicy(final long ticksToLive) {
            this.ticksToLive = ticksToLive;
        }

        @Override
        public long expirationTime(K k) {
            return now() + ticksToLive;
        }
    }

    public EntityExpiringSet(ExpirationPolicy<Entity> expirationPolicy) {
        super(Entity::getUniqueId, expirationPolicy);
    }
}
