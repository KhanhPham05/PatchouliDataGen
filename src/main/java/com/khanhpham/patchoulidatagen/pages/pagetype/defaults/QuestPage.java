package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.TranslatableComponent;

import javax.annotation.Nullable;

public class QuestPage implements PageType {
    @Nullable private final String trigger;
    @Nullable private final String title;
    @Nullable private final String text;

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
        private boolean triggerText = false;
        private boolean triggerComponent = false;

        @Nullable
        private String text = null;
        private boolean textText = false;
        private boolean textComponent = false;

        @Nullable
        private String title = null;
        private boolean isTitleText = false;
        private boolean isTitleComponent = false;

        public static Builder setup() {
            return new Builder();
        }

        public Builder trigger(String title) {
            if (!this.triggerComponent) {
                this.trigger = title;
                this.triggerText = true;
            } else throw new IllegalStateException("Trigger TranslatableComponent is already set");

            return this;
        }

        public Builder trigger(TranslatableComponent title) {
            if (!this.triggerText) {
                this.trigger = title.getKey();
                this.triggerComponent = true;
            } else throw new IllegalStateException("Trigger String is already set");
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
    }
}
