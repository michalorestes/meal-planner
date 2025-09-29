package com.fitness.meal_planner.features.ingredients.application.service;

import com.fitness.meal_planner.features.ingredients.application.dto.IngredientDto;
import com.fitness.meal_planner.features.ingredients.application.mapper.IngredientMapper;
import com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient;
import com.fitness.meal_planner.features.ingredients.domain.entity.IngredientType;
import com.fitness.meal_planner.features.ingredients.domain.entity.MeassurementUnit;
import com.fitness.meal_planner.features.ingredients.domain.repository.IngredientsRepositoryInterface;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class IngredientsProviderServiceTest {

    @Mock
    private IngredientsRepositoryInterface repositoryInterface;

    @Test
    void testCanReturnIngredientDTOs() {
        List<Ingredient> mockIngredients = List.of(
                new Ingredient(
                        1L,
                        "Chicken Breast",
                        IngredientType.PROTEIN,
                        "Brand A",
                        MeassurementUnit.GRAMS,
                        100.0,
                        new Macronutrients(165, 31.0, 0.0, 3.6, 0.0),
                        "http://example.com/chicken"
                ),
                new Ingredient(
                        2L,
                        "Carrot",
                        IngredientType.VEGETABLE,
                        "Brand B",
                        MeassurementUnit.GRAMS,
                        100.0,
                        new Macronutrients(41, 0.9, 9.6, 0.2, 2.8),
                        "http://example.com/carrot"
                )
        );

        Mockito.when(repositoryInterface.findAll()).thenReturn(mockIngredients);
        IngredientsProviderService service = new IngredientsProviderService(repositoryInterface, new IngredientMapper());

        List<IngredientDto> ingredientDTOs = service.getAllIngredients();

        Assertions.assertEquals(2, ingredientDTOs.size());

        Assertions.assertTrue(ingredientDTOs.stream().anyMatch(i -> i.name().equals("Chicken Breast")));
        Assertions.assertTrue(ingredientDTOs.stream().anyMatch(i -> i.name().equals("Carrot")));
    }

    @Test
    void testCanReturnEmptyList() {
        Mockito.when(repositoryInterface.findAll()).thenReturn(List.of());
        IngredientsProviderService service = new IngredientsProviderService(repositoryInterface, new IngredientMapper());

        List<IngredientDto> ingredientDTOs = service.getAllIngredients();

        Assertions.assertEquals(0, ingredientDTOs.size());
    }
}
