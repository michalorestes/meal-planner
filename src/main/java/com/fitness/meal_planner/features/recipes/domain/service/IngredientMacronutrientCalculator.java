package com.fitness.meal_planner.features.recipes.domain.service;

import com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IngredientMacronutrientCalculator implements IngredientMacronutrientCalculatorInterface {
    @Override
    public Macronutrients calculate(double amount, Ingredient ingredient) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        double scalingFactor = amount / ingredient.getUnitSize();
        Macronutrients baseMarcos = Objects.requireNonNull(
                ingredient.getMacronutrients(),
                "Macronutrients must not be null"
        );

        int calories = (int) Math.ceil(scalingFactor * baseMarcos.calories());
        double protein = scalingFactor * baseMarcos.protein();
        double carbohydrates = scalingFactor * baseMarcos.carbohydrates();
        double fats = scalingFactor * baseMarcos.fats();
        double fibre = scalingFactor * baseMarcos.fibre();

        return new Macronutrients(calories, protein, carbohydrates, fats, fibre);
    }
}
