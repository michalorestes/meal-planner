package com.fitness.meal_planner.features.recipes.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "recipes")
public class RecipeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false)
    private int servings;

    @Column(nullable = false, length = 10000)
    private String instructions;

    @Column(nullable = false)
    private int caloriesPerServing;

    @Column(nullable = false)
    private double proteinsPerServing;

    @Column(nullable = false)
    private double carbohydratesPerServing;

    @Column(nullable = false)
    private double fatsPerServing;

    @Column(nullable = false)
    private double fibrePerServing;
}
