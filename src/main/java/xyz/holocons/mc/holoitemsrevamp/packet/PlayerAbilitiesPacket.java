package xyz.holocons.mc.holoitemsrevamp.packet;

import com.comphenix.protocol.PacketType;
import org.bukkit.entity.Player;

public class PlayerAbilitiesPacket extends AbstractPacket {

    // https://nms.screamingsandals.org/1.18.2/net/minecraft/network/protocol/game/ClientboundPlayerAbilitiesPacket.html
    public PlayerAbilitiesPacket(Player player, boolean canInstaBuild) {
        super(PacketType.Play.Server.ABILITIES);
        handle.getBooleans()
            .write(0, player.isInvulnerable())
            .write(1, player.isFlying())
            .write(2, player.getAllowFlight())
            .write(3, canInstaBuild);
        handle.getFloat()
            .write(0, player.getFlySpeed() / 2)
            .write(1, player.getWalkSpeed() / 2);
    }
}
