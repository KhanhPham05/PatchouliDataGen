package com.khanhpham.patchoulidatagen.bookelement;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class BookHeader implements BookElement {
    private final String name;
    private final String landingText;
    @Nullable
    private final ResourceLocation bookTexture;
    @Nullable
    private final String fillerTexture;
    @Nullable
    private final String craftingTexture;
    @Nullable
    private final Integer textColor;
    @Nullable
    private final Integer headerColor;
    @Nullable
    private final Integer nameplateColor;
    @Nullable
    private final Integer linkColor;
    @Nullable
    private final Integer linkHoverColor;
    @Nullable
    private final Integer progressBarColor;
    @Nullable
    private final Integer progressBarBackground;
    @Nullable
    private final ResourceLocation openSound;
    @Nullable
    private final ResourceLocation flipSound;
    @Nullable
    private final Boolean showProgress;
    @Nullable
    private final String version;
    @Nullable
    private final String subtitle;
    @Nullable
    private final String creativeTab;
    @Nullable
    private final String advancementTab;
    @Nullable
    private final Boolean doNotGenerateBook;
    @Nullable
    private final Item customBookItem;
    @Nullable
    private final Boolean showToast;
    @Nullable
    private final Boolean useBlockyFont;
    @Nullable
    private final Boolean i18n;
    @Nullable
    private final Boolean pauseGame;
    @Nullable
    private final ResourceLocation icon;

    private final String bookId;


    public BookHeader(String bookId, String name, String landingText,
            @Nullable ResourceLocation bookTexture, @Nullable String fillerTexture,
            @Nullable String craftingTexture, @Nullable Integer textColor,
            @Nullable Integer headerColor, @Nullable Integer nameplateColor,
            @Nullable Integer linkColor, @Nullable Integer linkHoverColor,
            @Nullable Integer progressBarColor, @Nullable Integer progressBarBackground,
            @Nullable ResourceLocation openSound, @Nullable ResourceLocation flipSound,
            @Nullable Boolean showProgress, @Nullable String version, @Nullable String subtitle,
            @Nullable String creativeTab, @Nullable String advancementTab,
            @Nullable Boolean doNotGenerateBook, @Nullable Item customBookItem,
            @Nullable Boolean showToast, @Nullable Boolean useBlockyFont, @Nullable Boolean i18n,
            @Nullable Boolean pauseGame, @Nullable ResourceLocation icon) {
        this.name = name;
        this.landingText = landingText;
        this.bookTexture = bookTexture;
        this.fillerTexture = fillerTexture;
        this.craftingTexture = craftingTexture;
        this.textColor = textColor;
        this.headerColor = headerColor;
        this.nameplateColor = nameplateColor;
        this.linkColor = linkColor;
        this.linkHoverColor = linkHoverColor;
        this.progressBarColor = progressBarColor;
        this.progressBarBackground = progressBarBackground;
        this.openSound = openSound;
        this.flipSound = flipSound;
        this.showProgress = showProgress;
        this.version = version;
        this.subtitle = subtitle;
        this.creativeTab = creativeTab;
        this.advancementTab = advancementTab;
        this.doNotGenerateBook = doNotGenerateBook;
        this.customBookItem = customBookItem;
        this.showToast = showToast;
        this.useBlockyFont = useBlockyFont;
        this.i18n = i18n;
        this.pauseGame = pauseGame;
        this.icon = icon;
        this.bookId = bookId;
    }

    @Override
    public String getSaveName() {
        return "book";
    }

    public String getBookId() {
        return bookId;
    }

    public boolean isTranslatable() {
        return i18n != null;
    }

    /**
     * @see net.minecraft.world.item.CreativeModeTab
     */
    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("name", this.name);
        json.addProperty("landing_text", this.landingText);
        Utils.optional(json, "book_texture", bookTexture);
        Utils.optional(json, "filler_texture", fillerTexture);
        Utils.optional(json, "crafting_texture", craftingTexture);
        Utils.optional(json, "text_color", textColor);
        Utils.optional(json, "header_color", headerColor);
        Utils.optional(json, "nameplate_color", nameplateColor);
        Utils.optional(json, "link_color", linkColor);
        Utils.optional(json, "link_hover_color", linkHoverColor);
        Utils.optional(json, "progress_bar_color", progressBarColor);
        Utils.optional(json, "progress_bar_background", progressBarBackground);
        Utils.optional(json, "open_sound", openSound);
        Utils.optional(json, "flip_sound", flipSound);
        Utils.optional(json, "show_progress", showProgress);
        Utils.optional(json, "version", version);
        Utils.optional(json, "subtitle", subtitle);
        Utils.optional(json, "creative_tab", creativeTab);
        Utils.optional(json, "advancement_tab", advancementTab);
        Utils.optional(json, "dont_generate_book", doNotGenerateBook);
        if (customBookItem != null)
            Utils.optional(json, "custom_book_item", customBookItem.toString());

        Utils.optional(json, "show_toasts", showToast);
        Utils.optional(json, "use_blocky_font", useBlockyFont);
        Utils.optional(json, "i18n", i18n);
        Utils.optional(json, "pause_game", pauseGame);
        Utils.optional(json, "index_icon", icon);

        return json;
    }


    public static final class Builder {
        private boolean componentSet = false;
        private Component name;
        private Component landingText;

        private String nameText;
        private String landingTextText;
        @Nullable
        private ResourceLocation bookTexture;
        @Nullable
        private String fillerTexture;
        @Nullable
        private String craftingTexture;
        @Nullable
        private Integer textColor;
        @Nullable
        private Integer headerColor;
        @Nullable
        private Integer nameplateColor;
        @Nullable
        private Integer linkColor;
        @Nullable
        private Integer linkHoverColor;
        @Nullable
        private Integer progressBarColor;
        @Nullable
        private Integer progressBarBackground;
        @Nullable
        private ResourceLocation openSound;
        @Nullable
        private ResourceLocation flipSound;
        private Boolean showProgress;
        @Nullable
        private String version;
        @Nullable
        private String subtitle;
        @Nullable
        private String creativeTab;
        @Nullable
        private String advancementTab;
        private Boolean doNotGenerateBook;
        @Nullable
        private Item customBookItem;
        private Boolean showToast;
        private Boolean useBlockyFont;
        private Boolean i18n;
        private Boolean pauseGame = false;
        @Nullable
        private ResourceLocation icon = null;

        private String bookId;

        public static Builder header() {
            return new Builder();
        }

        /**
         * Use this in case you have i18n enabled, otherwise, use another
         * {@link #setBookComponentText(String, String, String)} method
         *
         * @param bookId - pass the mod id
         */
        public Builder setBookComponent(String bookId, Component name, Component landingText) {
            if (i18n != null) {
                this.name = name;
                this.landingText = landingText;
                this.bookId = bookId;
                this.componentSet = true;
            } else {
                throw new IllegalStateException(
                        "Used Component while i18n is not enabled yet, make sure to enable it before call this");
            }
            return this;
        }

        public Builder setBookComponentText(String bookId, String name, String landingText) {
            if (i18n != null) {
                if (!i18n && !componentSet) {
                    this.landingTextText = landingText;
                    this.nameText = name;
                    this.bookId = bookId;
                } else
                    throw new IllegalStateException(
                            "Used String text component while Component is already been set");
            }
            return this;
        }

        public Builder bookTexture(ResourceLocation textureLocation) {
            this.bookTexture = textureLocation;
            return this;
        }

        public Builder fillerTexture(String fillerTexture) {
            this.fillerTexture = fillerTexture;
            return this;
        }

        public Builder craftingTexture(String craftingTexture) {
            this.craftingTexture = craftingTexture;
            return this;
        }

        public Builder textColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder headerColor(int headerColor) {
            this.headerColor = headerColor;
            return this;
        }

        public Builder nameplateColor(int nameplateColor) {
            this.nameplateColor = nameplateColor;
            return this;
        }

        public Builder link(int linkColor, int linkHoverColor) {
            this.linkColor = linkColor;
            this.linkHoverColor = linkHoverColor;
            return this;
        }

        public Builder progressBarColor(int progressBarColor) {
            this.progressBarColor = progressBarColor;
            return this;
        }

        public Builder progressBarBackGround(int progressBarBackground) {
            this.progressBarBackground = progressBarBackground;
            return this;
        }

        public Builder openSound(ResourceLocation openSound) {
            this.openSound = openSound;
            return this;
        }

        public Builder flipSound(ResourceLocation flipSound) {
            this.flipSound = flipSound;
            return this;
        }

        public Builder indexIcon(ResourceLocation icon) {
            this.icon = icon;
            return this;
        }

        public Builder hideProgress() {
            this.showProgress = false;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder subtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder creativeTab(String tab) {
            this.creativeTab = tab;
            return this;
        }

        public Builder advancementTab(String advancementTab) {
            this.advancementTab = advancementTab;
            return this;
        }

        public Builder disableBook() {
            this.doNotGenerateBook = true;
            return this;
        }

        public Builder customBookItem(Item bookItem) {
            this.customBookItem = bookItem;
            return this;
        }

        public Builder disableToast() {
            this.showToast = false;
            return this;
        }

        public Builder useMinecraftFont() {
            this.useBlockyFont = true;
            return this;
        }

        public Builder enableI18n() {
            this.i18n = true;
            return this;
        }

        public Builder pauseGameWhenOpen() {
            this.pauseGame = true;
            return this;
        }

        public BookHeader build(Consumer<BookElement> consumer) {
            BookHeader header;

            if (i18n) {
                if (name == null || landingText == null) {
                    throw new IllegalStateException("Book name or landing text is unset !");
                } else {
                    header = new BookHeader(bookId, name.getString(), landingText.getString(),
                            bookTexture, fillerTexture, craftingTexture, textColor, headerColor,
                            nameplateColor, linkColor, linkHoverColor, progressBarColor,
                            progressBarBackground, openSound, flipSound, showProgress, version,
                            subtitle, creativeTab, advancementTab, doNotGenerateBook,
                            customBookItem, showToast, useBlockyFont, i18n, pauseGame, icon);
                }
            } else {
                if (nameText == null || landingTextText == null) {
                    throw new IllegalStateException("Book name or landing text is unset !");
                } else {

                    header = new BookHeader(bookId, nameText, landingTextText, bookTexture,
                            fillerTexture, craftingTexture, textColor, headerColor, nameplateColor,
                            linkColor, linkHoverColor, progressBarColor, progressBarBackground,
                            openSound, flipSound, showProgress, version, subtitle, creativeTab,
                            advancementTab, doNotGenerateBook, customBookItem, showToast,
                            useBlockyFont, i18n, pauseGame, icon);
                }
            }

            consumer.accept(header);
            return header;
        }

    }

}
