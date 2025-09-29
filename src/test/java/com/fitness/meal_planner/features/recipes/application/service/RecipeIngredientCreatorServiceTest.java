package com.fitness.meal_planner.features.recipes.application.service;

import com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient;
import com.fitness.meal_planner.features.ingredients.domain.entity.IngredientType;
import com.fitness.meal_planner.features.ingredients.domain.entity.MeassurementUnit;
import com.fitness.meal_planner.features.recipes.application.dto.RecipeIngredientDetail;
import com.fitness.meal_planner.features.recipes.domain.entity.RecipeIngredient;
import com.fitness.meal_planner.features.recipes.domain.port.IngredientsProviderPort;
import com.fitness.meal_planner.features.recipes.domain.service.IngredientMacronutrientCalculatorInterface;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RecipeIngredientCreatorServiceTest {
    @Mock
    private IngredientsProviderPort ingredientsProviderPort;

    @Mock
    private IngredientMacronutrientCalculatorInterface ingredientMacronutrientCalculatorInterface;

    @Test
    void testCanCreateRecipeIngredients() {
        Set<RecipeIngredientDetail> recipeIngredientDetails = Set.of(
                new RecipeIngredientDetail(1L, 200),
                new RecipeIngredientDetail(2L, 100)
        );

        List<Ingredient> ingredientsMock = List.of(
                new Ingredient(
                        1L,
                        "Chicken Breast",
                        IngredientType.PROTEIN,
                        "Brand A",
                        MeassurementUnit.GRAMS,
                        100.0,
                        new Macronutrients(400, 31.0, 0.0, 3.6, 0.0),
                        "http://example.com/chicken"
                ),
                new Ingredient(
                        2L,
                        "Lettuce",
                        IngredientType.VEGETABLE,
                        "Brand B",
                        MeassurementUnit.GRAMS,
                        100.0,
                        new Macronutrients(50, 1.0, 50.0, 3.6, 0.0),
                        "http://example.com/chicken"
                )
        );

        when(ingredientsProviderPort.getIngredients(Set.of(1L, 2L)))
                .thenReturn(ingredientsMock);

        when(ingredientMacronutrientCalculatorInterface.calculate(200, ingredientsMock.get(0)))
                .thenReturn(new Macronutrients(800, 62.0, 0.0, 7.2, 0.0));

        when(ingredientMacronutrientCalculatorInterface.calculate(100, ingredientsMock.get(1)))
                .thenReturn(new Macronutrients(15, 0.9, 2.9, 0.2, 1.2));

        RecipeIngredientCreatorService service = new RecipeIngredientCreatorService(ingredientsProviderPort, ingredientMacronutrientCalculatorInterface);
        Set<RecipeIngredient> result = service.create(recipeIngredientDetails);


        Set<RecipeIngredient> expectedRecipeIngredient = Set.of(
                new RecipeIngredient(1L, "Chicken Breast", 200.0, new Macronutrients(800, 62.0, 0.0, 7.2, 0.0)),
                new RecipeIngredient(2L, "Lettuce", 100.0, new Macronutrients(15, 0.9, 2.9, 0.2, 1.2))
        );

        assertEquals(2, result.size());
        assertEquals(expectedRecipeIngredient, result);
    }
}
