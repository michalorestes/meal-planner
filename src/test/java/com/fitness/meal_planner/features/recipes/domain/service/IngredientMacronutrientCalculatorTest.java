package com.fitness.meal_planner.features.recipes.domain.service;

import com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient;
import com.fitness.meal_planner.features.ingredients.domain.entity.IngredientType;
import com.fitness.meal_planner.features.ingredients.domain.entity.MeassurementUnit;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IngredientMacronutrientCalculatorTest {

    private IngredientMacronutrientCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new IngredientMacronutrientCalculator();
    }

    @Test
    @DisplayName("Should calculate macronutrients correctly for a given amount")
    void shouldCalculateMacronutrientsCorrectlyForGivenAmount() {
        Macronutrients baseMacros = new Macronutrients(120, 25.0, 2.0, 1.5, 0.0);
        Ingredient ingredient = new Ingredient(
                1L,
                "Chicken Breast",
                IngredientType.PROTEIN,
                "Brand",
                MeassurementUnit.GRAMS,
                100.0, // Base unit size
                baseMacros,
                null
        );
        double amount = 250; // We want to calculate for 250g

        Macronutrients result = calculator.calculate(amount, ingredient);

        Macronutrients expected = new Macronutrients(300, 62.5, 5.0, 3.75, 0.0);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should round up calories to the nearest integer")
    void shouldRoundUpCalories() {
        Macronutrients baseMacros = new Macronutrients(101, 10.0, 10.0, 10.0, 10.0);
        Ingredient ingredient = new Ingredient(1L, "Test Ingredient", IngredientType.PROTEIN, "Brand", MeassurementUnit.GRAMS, 100.0, baseMacros, null);
        double amount = 50; // 0.5x the unit size

        Macronutrients result = calculator.calculate(amount, ingredient);

        assertEquals(51, result.calories());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -100.0})
    @DisplayName("Should throw IllegalArgumentException for zero or negative amount")
    void shouldThrowExceptionForInvalidAmount(double invalidAmount) {
        Macronutrients baseMacros = new Macronutrients(100, 10, 10, 10, 10);
        Ingredient ingredient = new Ingredient(1L, "Test", IngredientType.PROTEIN, "Brand", MeassurementUnit.GRAMS, 100.0, baseMacros, null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculate(invalidAmount, ingredient)
        );
        assertEquals("Amount must be greater than 0", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw NullPointerException if ingredient macronutrients are null")
    void shouldThrowExceptionForNullMacronutrients() {
        Ingredient ingredientWithNullMacros = new Ingredient(1L, "Test", IngredientType.PROTEIN, "Brand", MeassurementUnit.GRAMS, 100.0, null, null);

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> calculator.calculate(100, ingredientWithNullMacros)
        );
        assertEquals("Macronutrients must not be null", exception.getMessage());
    }
}