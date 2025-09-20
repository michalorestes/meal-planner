package com.fitness.meal_planner.features.recipes.application.dto;

public record RecipePreviewDto(
        String name,
        int servings,
        int calories,
        double proteins,
        double carbohydrates,
        double fats,
        double fibre
) {
}
