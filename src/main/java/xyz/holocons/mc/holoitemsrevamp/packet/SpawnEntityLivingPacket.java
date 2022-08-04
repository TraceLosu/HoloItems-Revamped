package xyz.holocons.mc.holoitemsrevamp.packet;

import java.util.UUID;

import com.comphenix.protocol.PacketType;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class SpawnEntityLivingPacket extends AbstractPacket {

    // https://nms.screamingsandals.org/1.19.1/net/minecraft/network/protocol/game/ClientboundAddEntityPacket.html
    public SpawnEntityLivingPacket(int entityId, UUID uniqueId, EntityType entityType, Location location) {
        super(PacketType.Play.Server.SPAWN_ENTITY);
        // https://wiki.vg/Entity_metadata#Mobs
        handle.getIntegers()
            .write(0, entityId)             // id
            .write(1, 0)                    // xa
            .write(2, 0)                    // ya
            .write(3, 0)                    // za
            .write(4, 0);                   // data
        handle.getUUIDs()
            .write(0, uniqueId);            // uuid
        handle.getEntityTypeModifier()
            .write(0, entityType);          // type
        handle.getDoubles()
            .write(0, location.getX())      // x
            .write(1, location.getY())      // y
            .write(2, location.getZ());     // z
        handle.getBytes()
            .write(0, (byte)0)              // xRot
            .write(1, (byte)0)              // yRot
            .write(2, (byte)0);             // yHeadRot
    }
}
