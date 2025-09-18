package com.fitness.meal_planner.features.ingredients.domain.entity;

import java.util.Arrays;

public enum IngredientType {
    VEGETABLE("vegetable"),
    FRUITS("fruits"),
    DAIRY("dairy"),
    GRAINS("grains"),
    PROTEIN("protein");

    private final String type;

    IngredientType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static IngredientType fromString(String type) {
        return Arrays.stream(values())
                .filter(t -> t.type.equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown: " + type));
    }

    @Override
    public String toString() {
        return type;
    }
}
