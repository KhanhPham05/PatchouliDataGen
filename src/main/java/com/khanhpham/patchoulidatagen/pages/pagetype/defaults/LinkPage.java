package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;

/**
 * This page can display a link in the bottom of the page
 * 
 * @see <a href=
 *      "https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types#link-pages">Default
 *      Page Types - Link Page</a>
 */

public final class LinkPage implements PageType {
    private final String url;
    private final String linkText;

    public LinkPage(String url, String linkText) {
        this.url = url;
        this.linkText = linkText;
    }

    public static LinkPage setup(String url, String urlDisplayText) {
        return new LinkPage(url, urlDisplayText);
    }

    @Override
    public String getPageType() {
        return "link";
    }

    @Override
    public void toJson(JsonObject json) {
        json.addProperty("url", this.url);
        json.addProperty("like_text", this.linkText);
    }
}
