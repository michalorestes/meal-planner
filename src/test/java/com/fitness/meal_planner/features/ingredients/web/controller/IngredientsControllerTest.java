package com.fitness.meal_planner.features.ingredients.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fitness.meal_planner.features.ingredients.application.dto.IngredientDto;
import com.fitness.meal_planner.features.ingredients.application.service.IngredientsProviderService;
import com.fitness.meal_planner.features.ingredients.web.controllers.IngredientsController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@WebMvcTest(IngredientsController.class)
public class IngredientsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IngredientsProviderService ingredientsProviderService;

    @Test
    @WithMockUser
    public void testIngredientsAreDisplayed() throws Exception {
        IngredientDto ingredient1 = IngredientDto.builder()
            .id(1L)
            .name("Pasta")
            .type("grains")
            .brand("Barilla")
            .measurementUnit("g")
            .unitSize(100)
            .caloriesPerUnit(350)
            .proteinsPerUnit(12)
            .carbohydratesPerUnit(70)
            .fatsPerUnit(1.5)
            .fibrePerUnit(2.5)
            .shopLink("https://shop.example.com/pasta")
            .build();

        IngredientDto ingredient2 = IngredientDto.builder()
            .id(2L)
            .name("Milk")
            .type("dairy")
            .brand("DairyPure")
            .measurementUnit("ml")
            .unitSize(200)
            .caloriesPerUnit(130)
            .proteinsPerUnit(6.8)
            .carbohydratesPerUnit(9.6)
            .fatsPerUnit(4.8)
            .fibrePerUnit(0)
            .shopLink("https://shop.example.com/milk")
            .build();

        List<IngredientDto> ingredientDtos = List.of(ingredient1, ingredient2);
        Mockito.when(ingredientsProviderService.getAllIngredients()).thenReturn(ingredientDtos);

        mockMvc.perform(get("/ingredients/view"))
            .andExpect(status().isOk())
            .andExpect(view().name("ingredient/ingredientList"))
            .andExpect(model().attribute("ingredients", ingredientDtos));
    }
}
