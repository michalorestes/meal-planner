package com.fitness.meal_planner.features.recipes.application.service;

import com.fitness.meal_planner.features.recipes.application.dto.RecipeIngredientDetail;
import com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient;
import com.fitness.meal_planner.features.recipes.domain.entity.RecipeIngredient;
import com.fitness.meal_planner.features.recipes.domain.port.IngredientsProviderPort;
import com.fitness.meal_planner.features.recipes.domain.service.IngredientMacronutrientCalculatorInterface;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeIngredientCreatorService {
    private final IngredientsProviderPort ingredientsProviderPort;
    private final IngredientMacronutrientCalculatorInterface macronutrientCalculator;

    public Set<RecipeIngredient> create(Set<RecipeIngredientDetail> recipeIngredientDetails) {
        Set<Long> ingredientIds = recipeIngredientDetails
                .stream()
                .map(RecipeIngredientDetail::ingredientId)
                .collect(Collectors.toSet());

        Map<Long, Ingredient> ingredients = ingredientsProviderPort
                .getIngredients(ingredientIds)
                .stream()
                .collect(Collectors.toMap(Ingredient::getId, i -> i));

        return recipeIngredientDetails
                .stream()
                .map(details -> createRecipeIngredient(details, ingredients.get(details.ingredientId())))
                .collect(Collectors.toSet());
    }

    private RecipeIngredient createRecipeIngredient(RecipeIngredientDetail detail, Ingredient ingredient) {
        Macronutrients macronutrients = macronutrientCalculator.calculate(detail.amount(), ingredient);

        return new RecipeIngredient(
                ingredient.getId(),
                ingredient.getName(),
                detail.amount(),
                macronutrients
        );
    }
}
