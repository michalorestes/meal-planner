package com.fitness.meal_planner.features.ingredients.application.adapter;

import com.fitness.meal_planner.features.ingredients.application.service.IngredientsProviderService;
import com.fitness.meal_planner.features.ingredients.domain.entity.IngredientType;
import com.fitness.meal_planner.features.ingredients.domain.entity.MeassurementUnit;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientsProviderAdapterTest {

    @Mock
    private IngredientsProviderService ingredientsProviderService;

    @InjectMocks
    private IngredientsProviderAdapter ingredientsProviderAdapter;

    @Test
    void getIngredients_shouldCallServiceAndMapToRecipeDomainEntity() {
        var macros = new Macronutrients(10, 20, 30, 40, 5);
        var ingredientFromIngredientsDomain = new com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient(
                1L,
                "Chicken Fillets",
                IngredientType.PROTEIN,
                "Tesco",
                MeassurementUnit.GRAMS,
                100.0,
                macros,
                "www.shop.co.uk"
        );

        Set<Long> ingredientIds = Set.of(1L);
        when(ingredientsProviderService.getAllIngredientsByIds(ingredientIds))
                .thenReturn(List.of(ingredientFromIngredientsDomain));

        var expectedIngredientForRecipeDomain = new com.fitness.meal_planner.features.recipes.domain.entity.Ingredient(
                1L,
                "Chicken Fillets",
                100.0,
                macros
        );

        List<com.fitness.meal_planner.features.recipes.domain.entity.Ingredient> actualIngredients =
                ingredientsProviderAdapter.getIngredients(ingredientIds);

        assertThat(actualIngredients).hasSize(1);
        assertThat(actualIngredients.getFirst()).isEqualTo(expectedIngredientForRecipeDomain);
        assertThat(actualIngredients.getFirst().macronutrients()).isEqualTo(macros);
    }
}
