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
                .caloriesPerUnit(ingredient.getCaloriesPerUnit())
                .proteinsPerUnit(ingredient.getProteinsPerUnit())
                .carbohydratesPerUnit(ingredient.getCarbohydratesPerUnit())
                .fatsPerUnit(ingredient.getFatsPerUnit())
                .fibrePerUnit(ingredient.getFibrePerUnit())
                .shopLink(ingredient.getShopLink())
                .build();
    }
}