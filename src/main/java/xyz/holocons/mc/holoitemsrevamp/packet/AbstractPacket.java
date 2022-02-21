package xyz.holocons.mc.holoitemsrevamp.packet;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;

public abstract class AbstractPacket {

    protected final PacketContainer handle;

    protected AbstractPacket(PacketType packetType) {
        this.handle = new PacketContainer(packetType);
    }

    public final PacketContainer getHandle() {
        return handle;
    }

    public void sendPacket(Player player) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, handle);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void broadcastPacket() {
        ProtocolLibrary.getProtocolManager().broadcastServerPacket(handle);
    }
}
