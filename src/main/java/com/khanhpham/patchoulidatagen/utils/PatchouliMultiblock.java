package com.khanhpham.patchoulidatagen.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public final class PatchouliMultiblock implements Multiblock {
    private final List<List<String>> multiblock;
    private final Map<Character, String> mappings;

    public PatchouliMultiblock(List<List<String>> multiblock, Map<Character, String> mappings) {
        this.multiblock = multiblock;
        this.mappings = mappings;
    }

    public static Builder setup() {
        return Builder.multiblock();
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        JsonObject mapping = new JsonObject();
        for (Character key : mappings.keySet()) {
            mapping.addProperty(key.toString().toUpperCase(), mappings.get(key));
        }

        json.add("mapping", mapping);

        JsonArray allLayersArray = new JsonArray();

        multiblock.forEach(layer -> {
            JsonArray singleLayerArray = new JsonArray();
            layer.forEach(singleLayerArray::add);
            allLayersArray.add(singleLayerArray);
        });

        json.add("pattern", allLayersArray);

        return json;

    }

    public static final class Builder implements MultiblockBuilder {
        private final Set<Character> mappingCharacters = new HashSet<>();
        private final Map<Character, String> multiblockMappings = new HashMap<>();
        private final List<List<String>> multiblock = new ArrayList<>();
        private boolean isPatternAssigned = false;

        public static Builder multiblock() {
            return new Builder();
        }

        public Builder pattern(String... layerPattern) {
            List<String> layerMultiblock = new ArrayList<>(Arrays.asList(layerPattern));
            multiblock.add(layerMultiblock);
            isPatternAssigned = true;
            return this;
        }

        public Builder mapping(char c, Block block) {
            if (this.mappingCharacters.add(c))
                this.multiblockMappings.put(c, Objects.requireNonNull(block.getName()).toString());
            else
                throw new IllegalStateException("Duplicate mapping definition [" + c + ']');
            return this;
        }



        // This is not tested
        public <T extends Comparable<T>> Builder mapping(char c, Block block, Property<T> property,
                T value) {
            if (this.mappingCharacters.add(c))
                this.multiblockMappings.put(c, Objects.requireNonNull(block.getName()).toString()
                        + '[' + property.getName() + '=' + value.toString());
            else
                throw new IllegalStateException("Duplicate mapping definition [" + c + ']');
            return this;
        }

        public Builder mapping(char c, TagKey<Block> blockTag) {
            String tagId = '#' + blockTag.registry().location().toString();

            if (this.mappingCharacters.add(c))
                this.multiblockMappings.put(c, tagId);
            else
                throw new IllegalStateException("Duplicate mapping definition [" + c + ']');
            return this;
        }

        public PatchouliMultiblock build() {
            if (this.mappingCharacters.isEmpty()) {
                throw new IllegalStateException("No mapping is set");
            }

            if (isPatternAssigned) {

                AtomicReference<Byte> numberOfAnchor = new AtomicReference<>((byte) 0);
                this.multiblock.forEach(allLayers -> {

                    String[] strings = allLayers.toArray(new String[0]);
                    for (String s : strings) {
                        char[] c = s.toCharArray();
                        System.out.println("Checking " + s);
                        for (char c1 : c) {
                            if (c1 == '0') {
                                numberOfAnchor.getAndSet((byte) (numberOfAnchor.get() + 1));
                                System.out
                                    .println("Anchor Detected , total anchors : " + numberOfAnchor);
                            }
                        }
                    }

                });

                // for debugging
                // System.out.println("Finished Checking, total Anchors : " + numberOfAnchor);
                if (numberOfAnchor.get() > 1)
                    throw new IllegalStateException(
                            "Anchor point of multiblock is already assigned");
                else if (numberOfAnchor.get() == 0)
                    throw new IllegalStateException("Anchor point for multiblock is unset");

                return new PatchouliMultiblock(this.multiblock, multiblockMappings);
            } else
                throw new IllegalStateException("Multiblock pattern is unset");
        }
    }
}
