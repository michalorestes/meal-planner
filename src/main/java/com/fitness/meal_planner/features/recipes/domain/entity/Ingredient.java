package com.fitness.meal_planner.features.recipes.domain.entity;

import com.fitness.meal_planner.shared.valueobject.Macronutrients;

public record Ingredient(Long id, String name, double unitSize, Macronutrients macronutrients) {
}
