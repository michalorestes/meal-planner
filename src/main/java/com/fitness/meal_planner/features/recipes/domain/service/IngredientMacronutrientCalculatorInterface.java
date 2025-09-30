package com.fitness.meal_planner.features.recipes.domain.service;

import com.fitness.meal_planner.features.recipes.domain.entity.Ingredient;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;

public interface IngredientMacronutrientCalculatorInterface {
    Macronutrients calculate(double amount, Ingredient ingredient);
}
