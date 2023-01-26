package com.khanhpham.patchoulidatagen.bookelement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ItemLike;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public final class BookEntry implements BookElement {

    private final String saveName;
    @Nonnull
    private final BookCategory category;
    @Nonnull
    private final String name;
    @Nonnull
    private final ItemLike icon;
    @NonNull
    private final Set<PageType> pages;
    @Nullable
    private final String advancement;
    @Nullable
    private final String configFlag;
    @Nullable
    private final Boolean priority;
    @Nullable
    private final Boolean secret;
    @Nullable
    private final Boolean readByDefault;
    @Nullable
    private final Integer sortNum;
    @Nullable
    private final String turnIn;

    public BookEntry(String saveName, @Nonnull BookCategory category, @Nonnull String name,
            @Nonnull ItemLike icon, @NonNull Set<PageType> pages, @Nullable String advancement,
            @Nullable String configFlag, @Nullable Boolean priority, @Nullable Boolean secret,
            @Nullable Boolean readByDefault, @Nullable Integer sortNum, @Nullable String turnIn) {
        this.saveName = saveName;
        this.category = category;
        this.name = name;
        this.icon = icon;
        this.pages = pages;
        this.advancement = advancement;
        this.configFlag = configFlag;
        this.priority = priority;
        this.secret = secret;
        this.readByDefault = readByDefault;
        this.sortNum = sortNum;
        this.turnIn = turnIn;
    }

    public static Builder setup() {
        return Builder.entry();
    };

    @Override
    public String getSaveName() {
        return this.saveName;
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("name", this.name);
        json.addProperty("category", this.category.getCategoryId().toString());
        json.addProperty("icon", Objects.requireNonNull(this.icon.asItem().toString()));

        JsonArray pages = new JsonArray();
        this.pages.forEach(page -> {
            JsonObject pageJson = new JsonObject();
            pageJson.addProperty("type", page.getPageTypeId());
            page.toJson(pageJson);
            pages.add(pageJson);
        });
        json.add("pages", pages);

        Utils.optional(json, "advancement", this.advancement);
        Utils.optional(json, "flag", this.configFlag);
        Utils.optional(json, "priority", this.priority);
        Utils.optional(json, "secret", this.secret);
        Utils.optional(json, "read_by_default", this.readByDefault);
        Utils.optional(json, "sortnum", this.sortNum);
        Utils.optional(json, "turnin", this.turnIn);

        return json;
    }

    public static final class Builder {

        private final Set<PageType> pages = new HashSet<>();
        private BookCategory category;
        private String name;
        private ItemLike icon;
        private String advancement;
        private String configFlag;
        private Boolean priority;
        private Boolean secret;
        private Boolean readByDefault;
        private Integer sortNum;
        private String turnIn;
        private boolean isDisplaySet = false;

        private static Builder entry() {
            return new Builder();
        }

        public Builder category(BookCategory category) {
            this.category = category;
            return this;
        }

        public void setAdvancement(String advancement) {
            this.advancement = advancement;
        }

        public void setConfigFlag(String configFlag) {
            this.configFlag = configFlag;
        }

        public void setPriority(Boolean priority) {
            this.priority = priority;
        }

        public void setSecret(Boolean secret) {
            this.secret = secret;
        }

        public void setReadByDefault(Boolean readByDefault) {
            this.readByDefault = readByDefault;
        }

        public void setSortNum(Integer sortNum) {
            this.sortNum = sortNum;
        }

        public void setTurnIn(String turnIn) {
            this.turnIn = turnIn;
        }

        public Builder display(String entryName, ItemLike icon) {
            this.name = entryName;
            this.icon = icon;
            this.isDisplaySet = true;
            return this;
        }

        public Builder display(Component entryNameTranslatable, ItemLike icon) {
            this.name = entryNameTranslatable.getString();
            this.icon = icon;
            this.isDisplaySet = true;
            return this;
        }

        public Builder addPage(PageType page) {
            this.pages.add(page);
            return this;
        }

        public BookEntry build(Consumer<BookElement> consumer, String saveName) {
            if (category == null || !isDisplaySet) {
                throw new IllegalStateException("Missing parent category or entry display");
            }
            BookEntry entry = new BookEntry(saveName, category, name, icon, pages, advancement,
                    configFlag, priority, secret, readByDefault, sortNum, turnIn);
            consumer.accept(entry);
            return entry;
        }

    }

}
