package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;

/**
 * @see <a href="https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#crafting-recipe-pages"> Default Page Types - Spotlight Page</a>
 */
public class SpotlightPage implements PageType {
    private final ItemLike spotlightItem;

    @Nullable
    private final Boolean linkRecipe;

    @Nullable
    private final String title;

    @Nullable
    private final String text;

    private SpotlightPage(ItemLike spotlightItem, @Nullable Boolean linkRecipe, @Nullable String title, @Nullable String text) {
        this.spotlightItem = spotlightItem;
        this.linkRecipe = linkRecipe;
        this.title = title;
        this.text = text;
    }

    @Override
    public String getPageType() {
        return "spotlight";
    }

    @Override
    public void toJson(JsonObject json) {
        json.addProperty("item", this.spotlightItem.asItem().getRegistryName().toString());
        Utils.optional(json, "title", this.title);
        Utils.optional(json, "text", this.text);
        Utils.optional(json, "link_recipe", this.linkRecipe);
    }

    public static final class Builder {
        private ItemLike spotlightItem;
        @Nullable
        private Boolean linkRecipe;

        @Nullable
        private String text;
        private boolean textText = false;
        private boolean textComponent = false;

        @Nullable
        private String title = null;
        private boolean isTitleText = false;
        private boolean isTitleComponent = false;

        public static Builder setup() {
            return new Builder();
        }

        public Builder item(ItemLike spotlightItem) {
            this.spotlightItem = spotlightItem;
            return this;
        }

        public Builder linkRecipe(boolean linkable) {
            this.linkRecipe = linkable;
            return this;
        }

        public Builder title(String title) {
            if (!this.isTitleComponent) {
                this.title = title;
                this.isTitleText = true;
            } else throw new IllegalStateException("Title TranslatableComponent is already set");

            return this;
        }

        public Builder title(TranslatableComponent title) {
            if (!this.isTitleText) {
                this.title = title.getKey();
                this.isTitleComponent = true;
            } else throw new IllegalStateException("Title String is already set");
            return this;
        }

        public Builder text(String text) {
            if (!this.textComponent) {
                this.text = text;
                this.textText = true;
            } else throw new IllegalStateException("text TranslatableComponent is already set");
            return this;
        }

        public Builder text(TranslatableComponent text) {
            if (!this.textText) {
                this.text = text.getKey();
                this.textComponent = true;
            } else throw new IllegalStateException("Text String is already set");

            return this;
        }

        public SpotlightPage build() {
            if (spotlightItem != null) return new SpotlightPage(spotlightItem, linkRecipe, title, text);
            else throw new IllegalStateException("Missing spotlight item");
        }

    }
}
