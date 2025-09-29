package com.fitness.meal_planner.features.recipes.domain.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IngredientMacronutrients {
    private Long recipeId;
    private Long ingredientId;
    private double amount;
    private int calories;
    private double proteins;
    private double carbohydrates;
    private double fats;
    private double fibre;
}
