package com.fitness.meal_planner.features.recipes.application.service;

import com.fitness.meal_planner.features.recipes.application.dto.RecipePreviewDto;
import com.fitness.meal_planner.features.recipes.application.mapper.RecipeDtoMapper;
import com.fitness.meal_planner.features.recipes.domain.entity.Recipe;
import com.fitness.meal_planner.features.recipes.domain.repository.RecipeRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipesProviderService {
    private final RecipeRepositoryInterface recipeRepository;
    private final RecipeDtoMapper mapper;

    public List<RecipePreviewDto> getRecipePreviews() {
        List<Recipe> recipes = recipeRepository.getRecipes();

        return recipes.stream().map(mapper::fromDomain).toList();
    }
}
