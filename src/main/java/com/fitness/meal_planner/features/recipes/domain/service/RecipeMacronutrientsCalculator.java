package com.fitness.meal_planner.features.recipes.domain.service;

import com.fitness.meal_planner.features.recipes.domain.entity.RecipeIngredient;
import com.fitness.meal_planner.shared.service.CalorieRoundingService;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RecipeMacronutrientsCalculator implements RecipeMacronutrientsCalculatorInterface {
    @Override
    public Macronutrients calculate(int numberOfServings, Set<RecipeIngredient> recipeIngredients) {
        if (numberOfServings <= 0) {
            throw new IllegalArgumentException("Number of servings must be greater than 0");
        }

        Macronutrients totalMacronutrients = calculateTotalMacronutrients(recipeIngredients);

        return calculateMacronutrientsPerServing(numberOfServings, totalMacronutrients);
    }

    private Macronutrients calculateTotalMacronutrients(Set<RecipeIngredient> recipeIngredients) {
        int caloriesTota = 0;
        double proteinsTota = 0;
        double carbohydratesTota = 0;
        double fatsTota = 0;
        double fibreTota = 0;

        for (RecipeIngredient recipeIngredient : recipeIngredients) {
            Macronutrients macronutrients = recipeIngredient.macronutrients();
            caloriesTota += macronutrients.calories();
            proteinsTota += macronutrients.protein();
            carbohydratesTota += macronutrients.carbohydrates();
            fatsTota += macronutrients.fats();
            fibreTota += macronutrients.fibre();
        }

        return new Macronutrients(caloriesTota, proteinsTota, carbohydratesTota, fatsTota, fibreTota);
    }

    private Macronutrients calculateMacronutrientsPerServing(int servings, Macronutrients macronutrients) {
        int calories = macronutrients.calories();
        double carbohydrates = macronutrients.carbohydrates();
        double protein = macronutrients.protein();
        double fats = macronutrients.fats();
        double fibre = macronutrients.fibre();

        return new Macronutrients(
            calories == 0 ? calories : CalorieRoundingService.round((double) calories / servings),
            protein == 0 ? protein : protein / servings,
            carbohydrates == 0 ? carbohydrates : carbohydrates / servings,
            fats == 0 ? fats : fats / servings,
            fibre == 0 ? fibre : fibre / servings
        );
    }
}
