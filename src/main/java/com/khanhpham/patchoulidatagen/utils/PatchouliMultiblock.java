package com.khanhpham.patchoulidatagen.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public final class PatchouliMultiblock {
    private final List<List<String>> multiblock;
    private final Map<Character, String> mappings;

    public PatchouliMultiblock(List<List<String>> multiblock, Map<Character, String> mappings) {
        this.multiblock = multiblock;
        this.mappings = mappings;
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

    public static final class Builder {
        private final Set<Character> mappingCharacters = new HashSet<>();
        private final Map<Character, String> multiblockMappings = new HashMap<>();
        private final List<List<String>> multiblock = new ArrayList<>();
        private boolean isPatternAssigned = false;
        private int largest;

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
                this.multiblockMappings.put(c, Objects.requireNonNull(block.getRegistryName()).toString());
            else throw new IllegalStateException("Duplicate mapping definition [" + c + ']');
            return this;
        }

        //This is not tested,
        public Builder mapping(char c, Block block, Property<?> property, Object value) {
            if (this.mappingCharacters.add(c))
                this.multiblockMappings.put(c, Objects.requireNonNull(block.getRegistryName()).toString() + '[' + property.getName() + '=' + value.toString());
            else throw new IllegalStateException("Duplicate mapping definition [" + c + ']');
            return this;
        }

        public Builder mapping(char c, Tag.Named<Block> blockTag) {
            String tagId = '#' + blockTag.getName().toString();

            if (this.mappingCharacters.add(c))
                this.multiblockMappings.put(c, tagId);
            else throw new IllegalStateException("Duplicate mapping definition [" + c + ']');
            return this;
        }

        public PatchouliMultiblock build() {
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
                                System.out.println("Anchor Detected , total anchors : " + numberOfAnchor);
                            }
                        }
                    }

                });

                //for debugging
                //System.out.println("Finished Checking, total Anchors : " + numberOfAnchor);
                if (numberOfAnchor.get() > 1)
                    throw new IllegalStateException("Anchor point of multiblock is already assigned");
                else if (numberOfAnchor.get() == 0)
                    throw new IllegalStateException("Anchor point for multiblock is unset");

                return new PatchouliMultiblock(this.multiblock, multiblockMappings);
            } else throw new IllegalStateException("Multiblock pattern is unset");
        }

    }
}
