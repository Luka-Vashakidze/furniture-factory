package com.furniture.furniturefactory.enums;

public enum ProductCategory {
    CHAIR("Chair", true),
    TABLE("Table", true),
    SOFA("Sofa", false),
    SHELF("Shelf", true);

    private final String title;
    private final boolean flatPack;

    static {
        if (CHAIR.title == null || CHAIR.title.isBlank()) {
            throw new IllegalStateException("Invalid title");
        }
    }

    ProductCategory(String title, boolean flatPack) {
        this.title = title;
        this.flatPack = flatPack;
    }

    public String title() { return title; }

    public boolean isFlatPack() { return flatPack; }

    public String packingHint() {
        return flatPack ? "Ships flat-packed" : "Ships assembled";
    }
}