package com.fitness.meal_planner.features.recipes.domain.entity;

import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class Recipe {
    private Long id;
    private String name;
    private int servings;
    private String instructions;
    private Macronutrients macronutrients;
    private Set<RecipeIngredient> ingredients;
}
