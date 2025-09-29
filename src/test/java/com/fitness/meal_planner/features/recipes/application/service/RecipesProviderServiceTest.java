package com.fitness.meal_planner.features.recipes.application.service;

import com.fitness.meal_planner.features.recipes.application.dto.RecipePreviewDto;
import com.fitness.meal_planner.features.recipes.application.mapper.RecipeDtoMapper;
import com.fitness.meal_planner.features.recipes.data.mapper.RecipeMapper;
import com.fitness.meal_planner.features.recipes.data.model.RecipeModel;
import com.fitness.meal_planner.features.recipes.data.repository.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import({
        RecipeRepository.class,
        RecipeMapper.class,
        RecipeDtoMapper.class,
        RecipesProviderService.class
})
@ExtendWith(MockitoExtension.class)
class RecipesProviderServiceTest {

    @Autowired
    private RecipesProviderService recipesProviderService;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testServiceReturnsRecipePreviewDto() {
        RecipeModel tomatoSoupModel = new RecipeModel();
        tomatoSoupModel.setName("Tomato Soup");
        tomatoSoupModel.setServings(2);
        tomatoSoupModel.setInstructions("1. Step 1");
        tomatoSoupModel.setCaloriesPerServing(120);
        tomatoSoupModel.setCarbohydratesPerServing(22);
        tomatoSoupModel.setProteinsPerServing(10);
        tomatoSoupModel.setFatsPerServing(1);
        tomatoSoupModel.setFibrePerServing(22);

        RecipeModel cesarSalad = new RecipeModel();
        cesarSalad.setName("Cesar salad");
        cesarSalad.setServings(4);
        cesarSalad.setInstructions("1. Step 1");
        cesarSalad.setCaloriesPerServing(240);
        cesarSalad.setCarbohydratesPerServing(22);
        cesarSalad.setProteinsPerServing(10);
        cesarSalad.setFatsPerServing(11);
        cesarSalad.setFibrePerServing(11);

        entityManager.persist(tomatoSoupModel);
        entityManager.persist(cesarSalad);
        entityManager.flush();

        List<RecipePreviewDto> result = recipesProviderService.getRecipePreviews();

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().anyMatch(r -> r.name().equals("Tomato Soup")));
        Assertions.assertTrue(result.stream().anyMatch(r -> r.name().equals("Cesar salad")));
    }

    @Test
    void testServiceReturnsEmptyList() {
        List<RecipePreviewDto> result = recipesProviderService.getRecipePreviews();
        Assertions.assertEquals(0, result.size());
    }
}
