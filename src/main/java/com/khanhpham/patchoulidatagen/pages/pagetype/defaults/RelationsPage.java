package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.TranslatableComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 *  @see <a href="https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#relations-pages">Default Page Types - Relations Pages</a>
 */
public class RelationsPage implements PageType {
    private final String[] entries;

    @Nullable private final String title;

    @Nullable private final String text;

    public RelationsPage(String[] entries, @Nullable String title, @Nullable String text) {
        this.entries = entries;
        this.title = title;
        this.text = text;
    }

    @Override
    public String getPageType() {
        return "relations";
    }

    @Override
    public void toJson(JsonObject json) {
        JsonArray entriesArray = new JsonArray();
        for (String entry : entries) {
            entriesArray.add(entry);
        }

        json.add("entries", entriesArray);

        Utils.optional(json, "title", this.title);
        Utils.optional(json, "text", this.text);
    }

    public static final class Builder {
        private String[] entryArray;

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

        /**
         * For modders, pass your entry with your book name space is recommended,
         */
        public Builder entry(String... entriesId) {
            if (entriesId == null) {
                throw new IllegalStateException("You forgot to add at least one entry id");
            }
            this.entryArray = entriesId;
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

        public RelationsPage build() {
            if (entryArray != null) return new RelationsPage(entryArray, title, text);
            else throw new IllegalStateException("Required entries IDs are unset");
        }
    }
}
