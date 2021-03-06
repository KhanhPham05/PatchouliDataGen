package com.khanhpham.patchoulidatagen.utils;

import com.google.gson.JsonObject;

public interface Multiblock {
    JsonObject toJson();

    public interface MultiblockBuilder {
        Multiblock build();
    }
}
