package com.fitness.meal_planner.features.recipes.domain.repository;

import com.fitness.meal_planner.features.recipes.domain.entity.Recipe;

import java.util.List;

public interface RecipeRepositoryInterface {
    List<Recipe> getRecipes();
    Recipe save(Recipe recipe);
}
