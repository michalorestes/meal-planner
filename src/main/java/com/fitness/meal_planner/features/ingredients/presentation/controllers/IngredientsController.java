package com.fitness.meal_planner.features.ingredients.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IngredientsController {

    @GetMapping("/ingredients/view")
    @ResponseBody
    public String viewIngredients() {
        return "Hello World";
    }
    
}
