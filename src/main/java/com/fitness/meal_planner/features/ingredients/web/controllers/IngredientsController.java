package com.fitness.meal_planner.features.ingredients.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fitness.meal_planner.features.ingredients.application.dto.IngredientDto;
import com.fitness.meal_planner.features.ingredients.application.service.IngredientsProviderService;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class IngredientsController {

    private final IngredientsProviderService ingredientsProviderService;

    @GetMapping("/ingredients/view")
    public String viewIngredients(Model model) {

        List<IngredientDto> ingredientDtos = ingredientsProviderService.getAllIngredients();

        model.addAttribute("ingredients", ingredientDtos);

        return "ingredient/ingredientList";
    }
    
}
