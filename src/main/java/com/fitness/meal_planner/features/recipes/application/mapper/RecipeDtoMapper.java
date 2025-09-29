package com.fitness.meal_planner.features.recipes.application.mapper;

import com.fitness.meal_planner.features.recipes.application.dto.RecipePreviewDto;
import com.fitness.meal_planner.features.recipes.domain.entity.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeDtoMapper {
    public RecipePreviewDto fromDomain(Recipe recipe) {
        return new RecipePreviewDto(
                recipe.getName(),
                recipe.getServings(),
                recipe.getMacronutrients().calories(),
                recipe.getMacronutrients().protein(),
                recipe.getMacronutrients().carbohydrates(),
                recipe.getMacronutrients().fats(),
                recipe.getMacronutrients().fibre()
        );
    }
}
