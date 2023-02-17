package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;

/**
 * This page is used to display a specific item in a page
 * 
 * @see <a href=
 *      "https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#crafting-recipe-pages">
 *      Default Page Types - Spotlight Page</a>
 */
public final class SpotlightPage implements PageType {
    private final ItemLike spotlightItem;

    @Nullable
    private final Boolean linkRecipe;

    @Nullable
    private final String title;

    @Nullable
    private final String text;

    private SpotlightPage(ItemLike spotlightItem, @Nullable Boolean linkRecipe,
            @Nullable String title, @Nullable String text) {
        this.spotlightItem = spotlightItem;
        this.linkRecipe = linkRecipe;
        this.title = title;
        this.text = text;
    }

    @Override
    public String getPageType() {
        return "spotlight";
    }

    public static Builder setup() {
        return Builder.setup();
    }

    @Override
    public void toJson(JsonObject json) {
        json.addProperty("item", this.spotlightItem.asItem().toString());
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

        @Nullable
        private String title = null;

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
            this.title = title;
            return this;
        }

        public Builder title(Component title) {
            this.title = title.getString();
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder text(Component text) {
            this.text = text.getString();
            return this;
        }

        public SpotlightPage build() {
            if (spotlightItem != null)
                return new SpotlightPage(spotlightItem, linkRecipe, title, text);
            else
                throw new IllegalStateException("Missing spotlight item");
        }

    }
}
