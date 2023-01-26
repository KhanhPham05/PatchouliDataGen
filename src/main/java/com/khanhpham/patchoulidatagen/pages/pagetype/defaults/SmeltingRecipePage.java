package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;

/**
 * this page is used to display a smelting recipe
 *
 * @see <a href=
 *      "https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#smelting-recipe-pages">Default
 *      Page Types - Smelting Recipe Pages</a>
 */
public final class SmeltingRecipePage implements PageType {
    private final ItemLike smeltingItem;

    @Nullable
    private final ItemLike smeltingItem2;

    @Nullable
    private final String title;

    @Nullable
    private final String text;

    public SmeltingRecipePage(ItemLike smeltingItem, @Nullable ItemLike smeltingItem2,
            @Nullable String title, @Nullable String text) {
        this.smeltingItem = smeltingItem;
        this.smeltingItem2 = smeltingItem2;
        this.title = title;
        this.text = text;
    }

    public static Builder setup() {
        return Builder.setup();
    }

    @Override
    public String getPageType() {
        return "smelting";
    }

    @Override
    public void toJson(JsonObject json) {
        json.addProperty("recipe", this.smeltingItem.asItem().toString());
        if (this.smeltingItem2 != null) {
            json.addProperty("recipe2", this.smeltingItem2.asItem().toString());
        }

        Utils.optional(json, "title", this.title);
        Utils.optional(json, "text", this.text);
    }

    public static final class Builder {
        // MANDATORY
        private ItemLike smeltingItem;

        @Nullable
        private ItemLike smeltingItem2;

        @Nullable
        private String title;

        @Nullable
        private String text;

        private static Builder setup() {
            return new Builder();
        }

        public Builder mainRecipe(ItemLike result) {
            this.smeltingItem = result;
            return this;
        }

        public Builder secondaryRecipe(ItemLike result) {
            this.smeltingItem2 = result;
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

        public SmeltingRecipePage build() {
            if (smeltingItem == null) {
                throw new IllegalStateException("main smelting recipe is unset");
            } else {
                return new SmeltingRecipePage(smeltingItem, smeltingItem2, title, text);
            }
        }
    }


}

