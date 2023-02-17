package com.khanhpham.patchoulidatagen.bookelement;

import com.google.gson.JsonObject;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class BookCategory implements BookElement {
    private final String title;
    private final String description;
    private final ItemLike pageIcon;
    private final @Nullable Integer sortnum;
    private final Boolean secret;
    private final String saveName;
    private final BookHeader header;

    private BookCategory(BookHeader header, String title, String description, ItemLike pageIcon,
            @Nullable Integer sortNum, Boolean hidden, String saveName) {
        this.title = title;
        this.description = description;
        this.pageIcon = pageIcon;
        this.sortnum = sortNum;
        this.secret = hidden;
        this.saveName = saveName;
        this.header = header;
    }

    public boolean isTranslatable() {
        return header.isTranslatable();
    }

    @Override
    public String getSaveName() {
        return saveName;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("name", this.title);
        object.addProperty("description", this.description);
        object.addProperty("icon", ForgeRegistries.ITEMS.getKey(pageIcon.asItem()).toString());
        if (sortnum != null) {
            object.addProperty("sortnum", sortnum);
        }
        object.addProperty("secret", secret);
        return object;
    }

    public ResourceLocation getCategoryId() {
        return new ResourceLocation(header.getBookId(), getSaveName());
    }

    public static final class Builder {
        private BookHeader bookHeader;
        private String title;
        private String description;
        private ItemLike pageIcon;
        private Integer sortnum = null;
        private Boolean secret = false;

        private boolean isDisplayComponentSet = false;
        private boolean isDisplayStringSet = false;

        private boolean isDisplaySet = false;

        private Builder() {}

        public static Builder category() {
            return new BookCategory.Builder();
        }

        public Builder bookHeader(BookHeader bookHeader) {
            this.bookHeader = bookHeader;
            return this;
        }

        public Builder setDisplay(Component title, MutableComponent description, ItemLike icon) {
            if (bookHeader.isTranslatable() && !this.isDisplayStringSet) {
                this.isDisplayComponentSet = true;
                this.title = title.getString();
                this.description = description.getString();
            } else {
                throw new IllegalStateException(
                        "Use Component components while i18n is not enabled yet OR String component has been already set");
            }
            this.pageIcon = icon;
            this.isDisplaySet = true;
            return this;
        }

        public Builder setDisplay(String title, String description, ItemLike icon) {
            if (!this.isDisplayComponentSet) {
                this.isDisplayStringSet = true;
                this.title = title;
                this.description = description;
                this.isDisplaySet = true;
            } else
                throw new IllegalStateException("A Component component is already set");

            this.pageIcon = icon;
            return this;
        }

        public Builder sortNum(int num) {
            this.sortnum = num;
            return this;
        }

        public Builder secret(boolean isSecret) {
            this.secret = isSecret;
            return this;
        }

        public BookCategory save(Consumer<BookElement> consumer, String saveName) {
            return build(consumer, saveName);
        }

        private BookCategory build(Consumer<BookElement> consumer, String saveName) {
            if (!isDisplaySet || bookHeader == null) {
                throw new IllegalStateException(
                        "Category components and parent book file is unset");
            }

            BookCategory category = new BookCategory(bookHeader, title, description, pageIcon,
                    sortnum, secret, saveName);
            consumer.accept(category);
            return category;
        }
    }
}
