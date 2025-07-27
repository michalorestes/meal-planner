package com.fitness.meal_planner.features.ingredients.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fitness.meal_planner.features.ingredients.presentation.controllers.IngredientsController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

@WebMvcTest(IngredientsController.class)
public class IngredientsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIngredientsAreDisplayed() throws Exception {
        mockMvc.perform(get("/ingredients/view"))
            .andExpect(status().isOk());
    }
}
