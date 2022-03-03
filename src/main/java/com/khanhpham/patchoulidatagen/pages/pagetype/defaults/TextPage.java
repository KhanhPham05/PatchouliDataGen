package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.TranslatableComponent;

import javax.annotation.Nullable;

/**
 * @see <a href="https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#text-pages">Default Page Types - Text Pages</a>
 */
public class TextPage implements PageType {
    private final String text;

    @Nullable private final String title;

    public TextPage(String text, @Nullable String title) {
        this.text = text;
        this.title = title;
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
        private boolean textText = false;
        private boolean textComponent = false;

        @Nullable
        private String title = null;
        private boolean isTitleText = false;
        private boolean isTitleComponent = false;

        public static Builder setup() {
            return new Builder();
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

        public TextPage build() {
            if (this.text == null) {
                throw new IllegalStateException("Text for text page is unset, it is mandatory");
            } else return new TextPage(this.text, this.title);
        }

    }
}
