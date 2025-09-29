package com.fitness.meal_planner.features.recipes.application.service;

import com.fitness.meal_planner.features.recipes.domain.entity.Recipe;
import com.fitness.meal_planner.features.recipes.domain.entity.RecipeIngredient;
import com.fitness.meal_planner.features.recipes.domain.service.RecipeMacronutrientsCalculatorInterface;
import com.fitness.meal_planner.features.recipes.application.command.CreateRecipeCommand;
import com.fitness.meal_planner.features.recipes.domain.repository.RecipeRepositoryInterface;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class RecipeCreatorService {

    private final RecipeRepositoryInterface recipeRepository;
    private final RecipeMacronutrientsCalculatorInterface recipeMacronutrientsCalculator;
    private final RecipeIngredientCreatorService recipeIngredientCreatorService;

    public void createRecipe(CreateRecipeCommand command) {
        Set<RecipeIngredient> recipeIngredients = recipeIngredientCreatorService.create(command.recipeIngredientDetails());
        Macronutrients recipeMacronutrients = recipeMacronutrientsCalculator.calculate(command.servingsNumber(), recipeIngredients);

        Recipe recipe = Recipe.builder()
                .name(command.recipeName())
                .servings(command.servingsNumber())
                .instructions(command.cookingInstructions())
                .macronutrients(recipeMacronutrients)
                .ingredients(recipeIngredients)
                .build();

        recipeRepository.save(recipe);
    }
}
