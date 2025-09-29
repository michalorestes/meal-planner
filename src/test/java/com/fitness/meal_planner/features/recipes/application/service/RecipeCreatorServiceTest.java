package com.fitness.meal_planner.features.recipes.application.service;

import com.fitness.meal_planner.features.recipes.application.command.CreateRecipeCommand;
import com.fitness.meal_planner.features.recipes.application.dto.RecipeIngredientDetail;
import com.fitness.meal_planner.features.recipes.domain.entity.Recipe;
import com.fitness.meal_planner.features.recipes.domain.entity.RecipeIngredient;
import com.fitness.meal_planner.features.recipes.domain.repository.RecipeRepositoryInterface;
import com.fitness.meal_planner.features.recipes.domain.service.RecipeMacronutrientsCalculatorInterface;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeCreatorServiceTest {

    @Mock
    private RecipeRepositoryInterface recipeRepository;

    @Mock
    private RecipeMacronutrientsCalculatorInterface recipeMacronutrientsCalculator;

    @Mock
    private RecipeIngredientCreatorService recipeIngredientCreatorService;

    @InjectMocks
    private RecipeCreatorService recipeCreatorService;

    @Test
    void testCanCreateRecipe() {
        // Given
        var recipeIngredientDetails = Set.of(
                new RecipeIngredientDetail(1L, 200),
                new RecipeIngredientDetail(2L, 150)
        );

        var command = new CreateRecipeCommand(
                "Chicken Salad",
                4,
                "1. Cook chicken. 2. Chop lettuce. 3. Mix.",
                recipeIngredientDetails
        );

        var recipeIngredients = Set.of(
                new RecipeIngredient(1L, "Chicken", 200, new Macronutrients(800, 62, 0, 7.2, 0)),
                new RecipeIngredient(2L, "Lettuce", 150, new Macronutrients(22, 1.3, 4.3, 0.3, 1.9))
        );

        var totalMacronutrients = new Macronutrients(822, 63.3, 4.3, 7.5, 1.9);

        when(recipeIngredientCreatorService.create(command.recipeIngredientDetails()))
                .thenReturn(recipeIngredients);
        when(recipeMacronutrientsCalculator.calculate(command.servingsNumber(), recipeIngredients))
                .thenReturn(totalMacronutrients);

        // When
        recipeCreatorService.createRecipe(command);

        // Then
        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(recipeRepository).save(recipeCaptor.capture());

        Recipe savedRecipe = recipeCaptor.getValue();
        assertEquals(command.recipeName(), savedRecipe.getName());
        assertEquals(command.servingsNumber(), savedRecipe.getServings());
        assertEquals(command.cookingInstructions(), savedRecipe.getInstructions());
        assertEquals(recipeIngredients, savedRecipe.getIngredients());
        assertEquals(totalMacronutrients, savedRecipe.getMacronutrients());
    }
}
