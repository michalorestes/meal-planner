package com.fitness.meal_planner.features.recipes.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Recipe {
    private Long id;
    private String name;
    private int servings;
    private String instructions;
    private int calories;
    private double protein;
    private double carbohydrates;
    private double fats;
    private double fibre;
}
