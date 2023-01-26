package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * This page includes links to pages related
 *
 * @see <a href=
 *      "https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#relations-pages">Default
 *      Page Types - Relations Pages</a>
 */
public final class RelationsPage implements PageType {
    private final String[] entries;

    @Nullable
    private final String title;

    @Nullable
    private final String text;

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
        private String[] entryArray = new String[] {};

        @Nullable
        private String text;

        @Nullable
        private String title = null;

        public static Builder setup() {
            return new Builder();
        }

        /**
         * For modders, pass your entry with your book name space is recommended.
         *
         * @deprecated For modders, use {@link RelationsPage.Builder#entry(String, String...)}
         */
        public Builder entry(String... entriesId) {
            if (entriesId == null) {
                throw new IllegalStateException("You forgot to add at least one entry id");
            }
            this.entryArray = entriesId;
            return this;
        }

        public Builder entry(String modid, String... entryId) {
            List<String> entries = new ArrayList<>();
            for (String s : entryId) {
                String s1 = modid + s;
                entries.add(s1);
            }

            this.entryArray = entries.toArray(new String[0]);
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

        public RelationsPage build() {
            if (entryArray.length != 0)
                return new RelationsPage(entryArray, title, text);
            else
                throw new IllegalStateException("Required entries IDs are unset");
        }
    }
}
