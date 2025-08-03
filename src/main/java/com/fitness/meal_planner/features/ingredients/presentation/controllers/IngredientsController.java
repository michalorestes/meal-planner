package com.fitness.meal_planner.features.ingredients.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IngredientsController {

    @GetMapping("/ingredients/view")
    public String viewIngredients() {
        return "ingredient/ingredientList";
    }
    
}
