package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

/**
 * This page is only for text displaying, useful for documentation
 * 
 * @see <a href=
 *      "https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#text-pages">Default
 *      Page Types - Text Pages</a>
 */
public final class TextPage implements PageType {
    private final String text;

    @Nullable
    private final String title;

    public TextPage(String text, @Nullable String title) {
        this.text = text;
        this.title = title;
    }

    public static Builder setup() {
        return Builder.setup();
    }

    @Override
    public String getPageType() {
        return "text";
    }

    @Override
    public void toJson(JsonObject json) {
        json.addProperty("text", this.text);

        if (title != null) {
            json.addProperty("title", this.title);
        }
    }

    public static final class Builder {
        private String text;

        @Nullable
        private String title = null;

        public static Builder setup() {
            return new Builder();
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

        public TextPage build() {
            if (this.text == null) {
                throw new IllegalStateException("Text for text page is unset, it is mandatory");
            } else
                return new TextPage(this.text, this.title);
        }

    }
}
