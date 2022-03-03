package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;

/**
 * @see <a href="https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#crafting-recipe-pages">Default Page Types - Smelting Recipe Pages</a>
 */
public class SmeltingRecipePage implements PageType {
    private final ItemLike smeltingItem;

    @Nullable
    private final ItemLike smeltingItem2;

    @Nullable
    private final String title;

    @Nullable
    private final String text;

    public SmeltingRecipePage(ItemLike smeltingItem, @Nullable ItemLike smeltingItem2, @Nullable String title, @Nullable String text) {
        this.smeltingItem = smeltingItem;
        this.smeltingItem2 = smeltingItem2;
        this.title = title;
        this.text = text;
    }

    @Override
    public String getPageType() {
        return "smelting";
    }

    @Override
    public void toJson(JsonObject json) {
        json.addProperty("recipe", this.smeltingItem.asItem().getRegistryName().toString());
        if (this.smeltingItem2 != null) {
            json.addProperty("recipe2", this.smeltingItem2.asItem().getRegistryName().toString());
        }

        Utils.optional(json, "title", this.title);
        Utils.optional(json, "text", this.text);
    }

    public static final class Builder {


        private ItemLike smeltingItem;


        @Nullable
        private ItemLike smeltingItem2;

        @Nullable
        private String title;
        private boolean isTitleComponent = false;
        private boolean isTitleText = false;

        @Nullable
        private String text;
        private boolean textText = false;
        private boolean textComponent = false;

        public static Builder setup() {
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

        public SmeltingRecipePage build() {
            if (smeltingItem == null) {
                throw new IllegalStateException("main smelting recipe is unset");
            }  else {
                return new SmeltingRecipePage(smeltingItem, smeltingItem2, title, text);
            }
        }
    }


}

