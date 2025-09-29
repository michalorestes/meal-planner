package com.fitness.meal_planner.features.recipes.domain.service;

import com.fitness.meal_planner.features.recipes.domain.entity.RecipeIngredient;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecipeMacronutrientsCalculatorTest {

    private RecipeMacronutrientsCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new RecipeMacronutrientsCalculator();
    }

    @Test
    @DisplayName("Should calculate and divide total macronutrients by number of servings")
    void shouldCalculateMacrosPerServing() {
        // Given
        Set<RecipeIngredient> ingredients = Set.of(
                new RecipeIngredient(1L, "Chicken", 200, new Macronutrients(300, 50.0, 0.0, 10.0, 0.0)),
                new RecipeIngredient(2L, "Rice", 100, new Macronutrients(130, 2.7, 28.0, 0.3, 0.4)),
                new RecipeIngredient(3L, "Broccoli", 150, new Macronutrients(51, 4.0, 10.0, 0.5, 3.9))
        );
        int numberOfServings = 4;

        Macronutrients result = calculator.calculate(numberOfServings, ingredients);

        Macronutrients expected = new Macronutrients(121, 14.175, 9.5, 2.7, 1.075);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return total macronutrients when servings is 1")
    void shouldReturnTotalMacrosForSingleServing() {
        Set<RecipeIngredient> ingredients = Set.of(
                new RecipeIngredient(1L, "Steak", 250, new Macronutrients(450, 60.0, 1.0, 22.0, 0.0))
        );
        int numberOfServings = 1;

        Macronutrients result = calculator.calculate(numberOfServings, ingredients);

        Macronutrients expected = new Macronutrients(450, 60.0, 1.0, 22.0, 0.0);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return all zeros for an empty set of ingredients")
    void shouldReturnZerosForEmptyIngredients() {
        Set<RecipeIngredient> emptyIngredients = Collections.emptySet();
        int numberOfServings = 4;

        Macronutrients result = calculator.calculate(numberOfServings, emptyIngredients);

        Macronutrients expected = new Macronutrients(0, 0.0, 0.0, 0.0, 0.0);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    @DisplayName("Should throw IllegalArgumentException for zero or negative servings")
    void shouldThrowExceptionForInvalidServings(int invalidServings) {
        Set<RecipeIngredient> ingredients = Set.of(
                new RecipeIngredient(1L, "Test", 100, new Macronutrients(100, 10, 10, 10, 10))
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate(invalidServings, ingredients)
        );
        assertEquals("Number of servings must be greater than 0", exception.getMessage());
    }
}
