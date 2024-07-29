package xyz.holocons.mc.holoitemsrevamp.util;

import org.bukkit.block.BlockState;

import com.strangeone101.holoitemsapi.tracking.BlockLocation;

public class BlockStateExpiringSet extends ExpiringSet<BlockState, BlockLocation> {

    public BlockStateExpiringSet(final ExpirationPolicy<BlockState> expirationPolicy) {
        super(BlockLocation::new, expirationPolicy);
    }
}
