package com.fitness.meal_planner.features.ingredients.application.mapper;

import com.fitness.meal_planner.features.ingredients.application.dto.IngredientDto;
import com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {
    public IngredientDto toDto(Ingredient ingredient) {
        return IngredientDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .type(ingredient.getType().getType())
                .brand(ingredient.getBrand())
                .measurementUnit(ingredient.getMeassurementUnit().getUnit())
                .unitSize(ingredient.getUnitSize())
                .caloriesPerUnit(ingredient.getMacronutrients().calories())
                .proteinsPerUnit(ingredient.getMacronutrients().protein())
                .carbohydratesPerUnit(ingredient.getMacronutrients().carbohydrates())
                .fatsPerUnit(ingredient.getMacronutrients().fats())
                .fibrePerUnit(ingredient.getMacronutrients().fibre())
                .shopLink(ingredient.getShopLink())
                .build();
    }
}