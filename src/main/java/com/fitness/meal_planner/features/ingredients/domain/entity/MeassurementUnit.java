package com.fitness.meal_planner.features.ingredients.domain.entity;

import java.util.Arrays;

public enum MeassurementUnit {
    GRAMS("grams"),
    MILLILITERS("milliliters");

    private final String unit;

    MeassurementUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public static MeassurementUnit fromString(String unit) {
        return Arrays.stream(values())
                .filter(t -> t.unit.equalsIgnoreCase(unit))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown: " + unit));
    }

    @Override
    public String toString() {
        return unit;
    }
}
