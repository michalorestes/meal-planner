package com.fitness.meal_planner.features.recipes.domain.port;

import com.fitness.meal_planner.features.recipes.domain.entity.Ingredient;

import java.util.List;
import java.util.Set;

public interface IngredientsProviderPort {
    List<Ingredient> getIngredients(Set<Long> ingredientIds);
}
