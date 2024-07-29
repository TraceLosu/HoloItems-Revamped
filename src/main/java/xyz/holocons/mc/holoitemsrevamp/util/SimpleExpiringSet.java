package xyz.holocons.mc.holoitemsrevamp.util;

import java.util.function.Function;

public class SimpleExpiringSet<K> extends ExpiringSet<K, K> {

    public SimpleExpiringSet(final long ticksToLive) {
        super(Function.identity(), new ExpiringSet.ConstantTicksToLiveExpirationPolicy<>(ticksToLive));
    }
}
