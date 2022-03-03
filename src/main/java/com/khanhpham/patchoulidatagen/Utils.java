package com.khanhpham.patchoulidatagen;

import com.google.gson.JsonObject;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class Utils {
    public static void optional(JsonObject object, String key, @Nullable ResourceLocation loc) {
        if (loc != null) {
            object.addProperty(key, loc.toString());
        }
    }

    public static void optional(JsonObject object, String key, @Nullable Number value) {
        if (value != null) {
            object.addProperty(key, value);
        }
    }

    public static void optional(JsonObject object, String key, @Nullable String value) {
        if (value != null) {
            object.addProperty(key, value);
        }
    }

    public static void optional(JsonObject object, String key, @Nullable Boolean value) {
        if (value != null) {
            object.addProperty(key, value);
        }
    }

    public static void optional(JsonObject object, String key, @Nullable Component component) {
        if (component != null) {
            object.add(key, Component.Serializer.toJsonTree(component));
        }
    }
}
