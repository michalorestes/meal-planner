package com.fitness.meal_planner.features.ingredients.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
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
    private int caloriesPerUnit;
    private double proteinsPerUnit;
    private double carbohydratesPerUnit;
    private double fatsPerUnit;
    private double fibrePerUnit;
    private String shopLink; 
}
