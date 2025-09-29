package com.fitness.meal_planner.features.recipes.domain.entity;

import com.fitness.meal_planner.shared.valueobject.Macronutrients;

public record RecipeIngredient(
        Long ingredientId,
        String name,
        double amount,
        Macronutrients macronutrients
) {
}
