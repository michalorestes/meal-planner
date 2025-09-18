package com.fitness.meal_planner.features.ingredients.application.dto;

import lombok.Builder;

@Builder
public record IngredientDto(
        Long id,
        String name,
        String type,
        String brand,
        String measurementUnit,
        double unitSize,
        int caloriesPerUnit,
        double proteinsPerUnit,
        double carbohydratesPerUnit,
        double fatsPerUnit,
        double fibrePerUnit,
        String shopLink
) {
}