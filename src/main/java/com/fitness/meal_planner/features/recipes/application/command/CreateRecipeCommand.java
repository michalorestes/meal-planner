package com.fitness.meal_planner.features.recipes.application.command;

import com.fitness.meal_planner.features.recipes.application.dto.RecipeIngredientDetail;

import java.util.Set;

public record CreateRecipeCommand(
     String recipeName,
     int servingsNumber,
     String cookingInstructions,
     Set<RecipeIngredientDetail> recipeIngredientDetails
) {

}
