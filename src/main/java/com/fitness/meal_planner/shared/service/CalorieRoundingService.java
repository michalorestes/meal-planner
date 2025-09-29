package com.fitness.meal_planner.shared.service;

public class CalorieRoundingService {
    public static int round(double calories) {
        return (int) Math.ceil(calories);
    }
}
