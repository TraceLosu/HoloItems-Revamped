package com.strangeone101.holoitemsapi.tracking;

import com.google.gson.stream.JsonWriter;
import com.strangeone101.holoitemsapi.item.BlockAbility;
import com.strangeone101.holoitemsapi.item.CustomItemManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class GsonWriter extends JsonWriter {

    public GsonWriter(final File file) throws IOException {
        super(new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8)));
    }

    public void writeBlocks(final Map<TrackedBlock, BlockAbility> blocks) throws IOException {
        final var palette = CustomItemManager.getCustomBlocks();

        // Save palette, always.
        beginObject();
        name("palette");
        beginArray();
        for (final var stringKey : palette) {
            value(stringKey);
        }
        endArray();

        // Save blocks
        name("blocks");
        if (blocks.isEmpty()) {
            nullValue();
            endObject();
            return;
        }

        final Map<UUID, Map<Long, Map<Integer, BlockAbility>>> worlds = blocks
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(entry -> entry.getKey().worldKey(),
                        Collectors.groupingBy(entry -> entry.getKey().chunkKey(),
                                Collectors.toMap(entry -> entry.getKey().blockKey(), Map.Entry::getValue))));

        beginObject();
        for (final var world : worlds.entrySet()) {
            name(world.getKey().toString());
            beginObject();
            for (final var chunk : world.getValue().entrySet()) {
                name(chunk.getKey().toString());
                beginObject();
                for (final var block : chunk.getValue().entrySet()) {
                    name(block.getKey().toString());
                    value(palette.indexOf(block.getValue().getKey().getKey()));
                }
                endObject();
            }
            endObject();
        }
        endObject();
        endObject();
    }
}
