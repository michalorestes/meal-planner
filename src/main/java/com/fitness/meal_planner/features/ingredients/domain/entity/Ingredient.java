package com.fitness.meal_planner.features.ingredients.domain.entity;

import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Ingredient {
    private Long id;
    private String name;
    private IngredientType type;
    private String brand;
    private MeassurementUnit meassurementUnit;
    private double unitSize;
    private Macronutrients macronutrients;
    private String shopLink;
}
