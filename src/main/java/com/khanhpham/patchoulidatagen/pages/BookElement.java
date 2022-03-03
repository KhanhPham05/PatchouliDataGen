package com.khanhpham.patchoulidatagen.pages;

import com.google.gson.JsonObject;

public interface BookElement {
    String getSaveName();

    JsonObject toJson();
}
