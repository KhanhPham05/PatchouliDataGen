package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

/**
 * This page is used to display a crafting recipe
 * 
 * @see <a href=
 *      "https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#crafting-recipe-pages">
 *      Default Page Types - Crafting Recipe Pages</a>
 */
public final class CraftingRecipePage implements PageType {
    private final ResourceLocation recipe1;

    @Nullable
    private final ResourceLocation recipe2;

    @Nullable
    private final String title;

    @Nullable
    private final String text;

    private CraftingRecipePage(ResourceLocation recipe1, @Nullable ResourceLocation recipe2,
            @Nullable String title, @Nullable String text) {
        this.recipe1 = recipe1;
        this.recipe2 = recipe2;
        this.title = title;
        this.text = text;
    }

    @Override
    public String getPageType() {
        return "crafting";
    }

    @Override
    public void toJson(JsonObject json) {
        json.addProperty("recipe", recipe1.toString());
        Utils.optional(json, "recipe2", recipe2);
        Utils.optional(json, "title", this.title);
        Utils.optional(json, "text", this.text);
    }

    public static Builder setup() {
        return Builder.setup();
    }


    @Deprecated
    private void checkRecipe(JsonObject json, ResourceLocation recipe, String key) {
        Level level = Minecraft.getInstance().level;
        if (level != null) {
            level.getRecipeManager()
                .byKey(recipe)
                .ifPresentOrElse(r -> json.addProperty(key, r.toString()), () -> {
                    throw new IllegalStateException("Recipe [" + recipe + "] not present");
                });
        }
    }


    public static final class Builder {
        private ResourceLocation recipe1;

        @Nullable
        private ResourceLocation recipe2;

        private String text;
        @Nullable
        private String title = null;

        private static Builder setup() {
            return new Builder();
        }

        public Builder mainRecipe(ItemLike recipeByOutput) {
            this.recipe1 = ResourceLocation.tryParse(recipeByOutput.asItem().toString());
            return this;
        }

        public Builder mainRecipe(String recipeById) {
            this.recipe1 = new ResourceLocation(recipeById);
            return this;
        }

        public Builder secondaryRecipe(ItemLike recipeAsOutput) {
            this.recipe2 = ResourceLocation.tryParse(recipeAsOutput.asItem().toString());
            return this;
        }

        public Builder secondaryRecipe(String recipeById) {
            this.recipe2 = new ResourceLocation(recipeById);
            return this;
        }

        public Builder title(String titleByString) {
            this.title = titleByString;
            return this;
        }

        public Builder title(Component translatableTitle) {
            this.title = translatableTitle.getString();
            return this;
        }

        public Builder text(String textByString) {
            this.text = textByString;
            return this;
        }

        public Builder text(Component translatableText) {
            this.text = translatableText.getString();
            return this;
        }

        public CraftingRecipePage build() {
            if (recipe1 == null) {
                throw new IllegalStateException(
                        "Main crafting recipe is mandatory, but it is undefined");
            } else
                return new CraftingRecipePage(recipe1, recipe2, title, text);
        }
    }
}
