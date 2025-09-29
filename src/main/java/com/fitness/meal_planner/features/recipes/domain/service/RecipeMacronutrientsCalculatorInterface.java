package com.fitness.meal_planner.features.recipes.domain.service;

import com.fitness.meal_planner.features.recipes.domain.entity.RecipeIngredient;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;

import java.util.Set;

public interface RecipeMacronutrientsCalculatorInterface {
    Macronutrients calculate(int numberOfServings, Set<RecipeIngredient> ingredientMacronutrients);
}
