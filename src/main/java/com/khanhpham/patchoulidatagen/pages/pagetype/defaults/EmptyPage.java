package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;

import javax.annotation.Nullable;

/**
 * This is an empty page with no text
 * 
 * @see <a href=
 *      "https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#empty-pages">Default
 *      Page Types - Empty Pages</a>
 */
public final class EmptyPage implements PageType {
    @Nullable
    private final Boolean drawFiller;

    public EmptyPage(@Nullable Boolean drawFiller) {
        this.drawFiller = drawFiller;
    }

    public static EmptyPage setup() {
        return new EmptyPage(null);
    }

    public static EmptyPage setupWithFiller() {
        return new EmptyPage(Boolean.TRUE);
    }


    @Override
    public String getPageType() {
        return "empty";
    }

    @Override
    public void toJson(JsonObject json) {
        Utils.optional(json, "draw_filler", this.drawFiller);
    }
}
