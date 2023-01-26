package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

/**
 * This page is used to mark an entry with a "?" * @see <a href=
 * "https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#quest-pages">Defaylt
 * Page Types - Quest Pages</a>
 */
public final class QuestPage implements PageType {
    @Nullable
    private final String trigger;
    @Nullable
    private final String title;
    @Nullable
    private final String text;

    public QuestPage(@Nullable String trigger, @Nullable String title, @Nullable String text) {
        this.trigger = trigger;
        this.title = title;
        this.text = text;
    }

    @Override
    public String getPageType() {
        return "quest";
    }

    @Override
    public void toJson(JsonObject json) {
        Utils.optional(json, "Trigger", this.trigger);
        Utils.optional(json, "title", this.title);
        Utils.optional(json, "text", this.text);
    }

    public static final class Builder {
        @Nullable
        private String trigger;

        @Nullable
        private String text = null;

        @Nullable
        private String title = null;

        public static Builder setup() {
            return new Builder();
        }

        public Builder trigger(String title) {
            this.trigger = title;
            return this;
        }

        public Builder trigger(Component title) {
            this.trigger = title.getString();
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

        public QuestPage build() {
            return new QuestPage(trigger, title, text);
        }
    }
}
