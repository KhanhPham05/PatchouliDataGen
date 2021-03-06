package com.khanhpham.patchoulidatagen.bookelement;

public enum BookElementType {
    CATEGORIES("categories"),
    ENTRIES("entries");

    private final String name;

    BookElementType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
