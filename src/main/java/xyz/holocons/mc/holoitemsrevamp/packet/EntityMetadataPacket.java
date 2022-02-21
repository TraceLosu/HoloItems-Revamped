package xyz.holocons.mc.holoitemsrevamp.packet;

import java.util.Optional;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Registry;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class EntityMetadataPacket extends AbstractPacket {

    public class Metadata {

        private final WrappedDataWatcher watcher;

        // https://wiki.vg/Entity_metadata#Entity_Metadata_Format
        public Metadata() {
            this.watcher = new WrappedDataWatcher();
        }

        public WrappedDataWatcher getWatcher() {
            return watcher;
        }

        public void setByte(int index, byte data) {
            watcher.setObject(index, Registry.get(Byte.class), data, true);
        }

        public void setCustomName(Component name) {
            final var jsonComponent = GsonComponentSerializer.gson().serialize(name);
            final var chatComponent = WrappedChatComponent.fromJson(jsonComponent);
            final var optionalChatComponent = Optional.of(chatComponent.getHandle());
            watcher.setObject(2, Registry.getChatComponentSerializer(true), optionalChatComponent, true);
        }

        public void setCustomNameVisible(boolean visible) {
            watcher.setObject(3, Registry.get(Boolean.class), visible, true);
        }
    }

    // https://nms.screamingsandals.org/1.18.1/net/minecraft/network/protocol/game/ClientboundSetEntityDataPacket.html
    public EntityMetadataPacket(int entityId, Metadata metadata) {
        super(PacketType.Play.Server.ENTITY_METADATA);
        handle.getIntegers()
            .write(0, entityId);                                    // id
        handle.getWatchableCollectionModifier()
            .write(0, metadata.getWatcher().getWatchableObjects()); // packedItems
    }
}
