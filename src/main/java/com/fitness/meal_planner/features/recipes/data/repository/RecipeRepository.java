package com.fitness.meal_planner.features.recipes.data.repository;

import com.fitness.meal_planner.features.recipes.data.mapper.RecipeMapper;
import com.fitness.meal_planner.features.recipes.data.model.RecipeModel;
import com.fitness.meal_planner.features.recipes.domain.entity.Recipe;
import com.fitness.meal_planner.features.recipes.domain.repository.RecipeInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class RecipeRepository implements RecipeInterface {

    private final RecipeJpaRepository recipeJpaRepository;
    private final RecipeMapper mapper;

    @Override
    public List<Recipe> getRecipes() {
        Iterable<RecipeModel> recipeModels = recipeJpaRepository.findAll();
        List<Recipe> recipes = new ArrayList<>();
        recipeModels.forEach(model -> recipes.add(mapper.toDomain(model)));

        return recipes;
    }
}
