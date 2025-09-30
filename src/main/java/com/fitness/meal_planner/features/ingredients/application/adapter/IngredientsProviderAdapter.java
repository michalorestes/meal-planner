package com.fitness.meal_planner.features.ingredients.application.adapter;

import com.fitness.meal_planner.features.ingredients.application.service.IngredientsProviderService;
import com.fitness.meal_planner.features.recipes.domain.entity.Ingredient;
import com.fitness.meal_planner.features.recipes.domain.port.IngredientsProviderPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class IngredientsProviderAdapter implements IngredientsProviderPort {

    private final IngredientsProviderService ingredientsProviderService;

    @Override
    public List<Ingredient> getIngredients(Set<Long> ingredientIds) {
        var ingredientEntities = ingredientsProviderService.getAllIngredientsByIds(ingredientIds);

        return ingredientEntities.stream()
                .map(i -> new Ingredient(i.getId(), i.getName(), i.getUnitSize(), i.getMacronutrients()))
                .toList();
    }
}
