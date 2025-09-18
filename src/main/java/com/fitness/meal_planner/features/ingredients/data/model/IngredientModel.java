package com.fitness.meal_planner.features.ingredients.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ingredients")
public class IngredientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; 
    private String type;
    private String brand;
    private String measurementUnit;
    private double unitSize;
    private int caloriesPerUnit;
    private double proteinsPerUnit;
    private double carbohydratesPerUnit;
    private double fatsPerUnit;
    private double fibrePerUnit;
    private String shopLink; 
}
