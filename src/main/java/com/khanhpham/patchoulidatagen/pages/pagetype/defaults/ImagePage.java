package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.checkerframework.checker.units.qual.A;

import javax.annotation.Nullable;
import java.util.ArrayList;


/**
 * This page is used to display an image
 *
 * @see <a href=
 *      "https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#image-pages">Default
 *      Page Types - Images</a>
 */
public final class ImagePage implements PageType {
    private final ResourceLocation[] images;
    @Nullable
    private final String title;
    @Nullable
    private final Boolean border;
    @Nullable
    private final String text;

    private ImagePage(ResourceLocation[] images, @Nullable String title, @Nullable Boolean border,
            @Nullable String text) {
        this.images = images;
        this.title = title;
        this.border = border;
        this.text = text;
    }

    public static Builder setup() {
        return Builder.setup();
    }

    @Override
    public String getPageType() {
        return "image";
    }

    @Override
    public void toJson(JsonObject json) {
        JsonArray imageArray = new JsonArray();
        for (ResourceLocation image : this.images) {
            imageArray.add(image.toString());
        }

        json.add("images", imageArray);
        Utils.optional(json, "title", this.title);
        Utils.optional(json, "border", this.border);
        Utils.optional(json, "text", this.text);
    }

    public static final class Builder {
        private ResourceLocation[] images;

        @Nullable
        private Boolean border = null;

        @Nullable
        private String text;

        @Nullable
        private String title = null;

        private static Builder setup() {
            return new Builder();
        }

        public Builder addImage(ResourceLocation... images) {
            this.images = images;
            return this;
        }

        public Builder addImage(String... images) {
            ArrayList<ResourceLocation> i = new ArrayList<>();
            for (String image : images) {
                i.add(new ResourceLocation(image));
            }

            this.images = i.toArray(new ResourceLocation[0]);
            return this;
        }

        public Builder border(boolean border) {
            this.border = border;
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

        public ImagePage build() {
            if (images.length == 1 || images.length == 2) {
                return new ImagePage(images, title, border, text);
            }

            throw new IllegalStateException("Missing images or too many images for a page");
        }

    }
}
