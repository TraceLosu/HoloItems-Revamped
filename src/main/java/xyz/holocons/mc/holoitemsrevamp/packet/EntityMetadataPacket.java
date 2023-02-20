package xyz.holocons.mc.holoitemsrevamp.packet;

import java.util.List;
import java.util.Optional;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataValue;
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Registry;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class EntityMetadataPacket extends AbstractPacket {

    public static class Metadata {

        private final Int2ObjectArrayMap<WrappedDataValue> dataValues;

        // https://wiki.vg/Entity_metadata#Entity_Metadata_Format
        public Metadata() {
            this.dataValues = new Int2ObjectArrayMap<>();
        }

        public List<WrappedDataValue> toList() {
            return List.copyOf(dataValues.values());
        }

        private void setObject(int index, Object value, Class<?> clazz) {
            dataValues.put(index, new WrappedDataValue(index, Registry.get(clazz), value));
        }

        public void setByte(int index, byte value) {
            setObject(index, value, Byte.class);
        }

        public void setBoolean(int index, boolean value) {
            setObject(index, value, Boolean.class);
        }

        public void setVarInt(int index, int value) {
            setObject(index, value, Integer.class);
        }

        public void setCustomName(Component name) {
            final var jsonComponent = GsonComponentSerializer.gson().serialize(name);
            final var chatComponent = WrappedChatComponent.fromJson(jsonComponent);
            final var optionalChatComponent = Optional.of(chatComponent.getHandle());
            dataValues.put(2, new WrappedDataValue(2, Registry.getChatComponentSerializer(true), optionalChatComponent));
        }

        public void setCustomNameVisible() {
            setBoolean(3, true);
        }
    }

    // https://nms.screamingsandals.org/1.19.3/net/minecraft/network/protocol/game/ClientboundSetEntityDataPacket.html
    public EntityMetadataPacket(int entityId, Metadata metadata) {
        super(PacketType.Play.Server.ENTITY_METADATA);
        handle.getIntegers()
            .write(0, entityId);        // id
        handle.getDataValueCollectionModifier()
            .write(0, metadata.toList());  // packedItems
    }
}
