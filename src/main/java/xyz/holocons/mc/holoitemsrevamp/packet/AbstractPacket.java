package xyz.holocons.mc.holoitemsrevamp.packet;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;

public abstract class AbstractPacket {

    protected final PacketContainer handle;

    protected AbstractPacket(PacketType packetType) {
        this.handle = new PacketContainer(packetType);
    }

    public PacketContainer getHandle() {
        return handle;
    }

    public void sendPacket(Player player) {
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, getHandle());
    }

    public void broadcastNearbyPacket(Location origin, int maxObserverDistance) {
        ProtocolLibrary.getProtocolManager().broadcastServerPacket(getHandle(), origin, maxObserverDistance);
    }

    public void broadcastServerPacket() {
        ProtocolLibrary.getProtocolManager().broadcastServerPacket(getHandle());
    }
}
