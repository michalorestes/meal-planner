package com.fitness.meal_planner.shared.valueobject;

public record Macronutrients(
        int calories,
        double protein,
        double carbohydrates,
        double fats,
        double fibre
) {
}
