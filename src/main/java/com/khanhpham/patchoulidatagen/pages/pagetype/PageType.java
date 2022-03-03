package com.khanhpham.patchoulidatagen.pages.pagetype;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;

public interface PageType {
    String getPageType();

    /**
     * Overwrite this if you have a custom page type
     */
    default ResourceLocation getPageTypeLocation() {
        return new ResourceLocation("patchouli", getPageType());
    }

    default String getPageTypeId() {
        return getPageTypeLocation().toString();
    };

    void toJson(JsonObject json);
}
