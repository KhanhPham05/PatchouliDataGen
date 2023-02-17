package com.khanhpham.patchoulidatagen.pages.pagetype.defaults;

import com.google.gson.JsonObject;
import com.khanhpham.patchoulidatagen.Utils;
import com.khanhpham.patchoulidatagen.pages.pagetype.PageType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * @see <a href=
 *      "https://vazkiimods.github.io/Patchouli/docs/patchouli-basics/page-types/#entity-pages">
 *      Default Page Types - Entity Pages</a>
 */
public final class EntityPage implements PageType {
    private final String entity;
    @Nullable
    private final Float scale;
    @Nullable
    private final Float offset;
    @Nullable
    private final Boolean rotate;
    @Nullable
    private final Float defaultRotation;
    @Nullable
    private final String name;
    @Nullable
    private final String text;

    public EntityPage(String entity, @Nullable Float scale, @Nullable Float offset,
            @Nullable Boolean rotate, @Nullable Float defaultRotation, @Nullable String name,
            @Nullable String text) {
        this.entity = entity;
        this.scale = scale;
        this.offset = offset;
        this.rotate = rotate;
        this.defaultRotation = defaultRotation;
        this.name = name;
        this.text = text;
    }

    public static Builder setup() {
        return Builder.setup();
    }

    @Override
    public String getPageType() {
        return "entity";
    }

    @Override
    public void toJson(JsonObject json) {
        json.addProperty("entity", this.entity);
        Utils.optional(json, "scale", this.scale);
        Utils.optional(json, "offset", this.offset);
        Utils.optional(json, "rotate", this.rotate);
        Utils.optional(json, "default_rotation", this.defaultRotation);
        Utils.optional(json, "name", this.name);
        Utils.optional(json, "text", this.text);
    }

    public static final class Builder {
        private String entity;
        @Nullable
        private Float scale;
        @Nullable
        private Float offset;
        @Nullable
        private Boolean rotate;
        @Nullable
        private Float defaultRotation;

        @Nullable
        private String name;
        @Nullable
        private String text;

        private static Builder setup() {
            return new Builder();
        }

        public <T extends Entity> Builder entity(EntityType<T> entityType) {
            this.entity = Objects.requireNonNull(entityType.toString());
            return this;
        }

        public Builder scale(float scale) {
            this.scale = scale;
            return this;
        }

        public Builder offset(float offset) {
            this.offset = offset;
            return this;
        }

        public Builder nonRotatable() {
            this.rotate = false;
            return this;
        }

        public Builder defaultRotation(float defaultRotation) {
            this.defaultRotation = defaultRotation;
            return this;
        }

        public Builder name(String nameByString) {
            this.name = nameByString;
            return this;
        }

        public Builder name(Component translatableName) {
            this.name = translatableName.getString();
            return this;
        }

        public Builder text(String textByString) {
            this.text = textByString;
            return this;
        }

        public Builder text(Component translatableText) {
            this.text = translatableText.getString();
            return this;
        }

        public EntityPage build() {
            return new EntityPage(entity, scale, offset, rotate, defaultRotation, name, text);
        }
    }
}
