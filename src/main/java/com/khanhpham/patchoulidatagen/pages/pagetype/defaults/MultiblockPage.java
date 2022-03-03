package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.PatchouliMultiblock;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

/**
 * This page is used to display a multiblock structure
 * @see <a href="https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#multiblock-pages">Defaylt Page Types - Multiblock Pages</a>
 */
public class MultiblockPage implements PageType {
    private final String multiblockName;

    private final PatchouliMultiblock multiblock;

    @Nullable
    private final String multiblockId;

    @Nullable
    private final Boolean enableVisualize;

    @Nullable
    private final Component text;

    public MultiblockPage(String multiblockName, PatchouliMultiblock multiblock, @Nullable String multiblockId, @Nullable Boolean enableVisualize, @Nullable Component text) {
        this.multiblockName = multiblockName;
        this.multiblockId = multiblockId;
        this.multiblock = multiblock;
        this.enableVisualize = enableVisualize;
        this.text = text;
    }

    @Override
    public String getPageType() {
        return "multiblock";
    }

    @Override
    public void toJson(JsonObject json) {
        json.addProperty("name", this.multiblockName);
        Utils.optional(json, "multiblock_id", this.multiblockId);
        Utils.optional(json, "enable_visualize", this.enableVisualize);
        Utils.optional(json, "text", this.text);
        json.add("multiblock", this.multiblock.toJson());
    }

    public static final class Builder {
        private String multiblockName;
        private PatchouliMultiblock multiblock;
        private String multiblockId = null;
        private Boolean enableVisualize = null;
        private Component text = null;
        private boolean isMultiblockComponentSet = false;

        private Builder() {
        }

        public static Builder setup() {
            return new Builder();
        }

        public Builder multiblock(String multiblockName, PatchouliMultiblock.Builder multiblockBuilder) {
            this.multiblockName = multiblockName;
            this.multiblock = multiblockBuilder.build();
            this.isMultiblockComponentSet = true;
            return this;
        }

        public Builder multiblockId(String multiblockId) {
            this.multiblockId = multiblockId;
            return this;
        }

        public Builder disableVisualize() {
            this.enableVisualize = false;
            return this;
        }

        public Builder pageText(Component text) {
            this.text = text;
            return this;
        }

        public MultiblockPage build() {
            if (isMultiblockComponentSet)
                return new MultiblockPage(multiblockName, multiblock, multiblockId, enableVisualize, text);
            else throw new IllegalStateException("Multiblock component is unset");
        }
    }
}
