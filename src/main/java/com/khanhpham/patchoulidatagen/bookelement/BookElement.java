package com.khanhpham.patchoulidatagen.bookelement;

import com.google.gson.JsonObject;

public interface BookElement {
    String getSaveName();

    JsonObject toJson();
}
