package com.fitness.meal_planner.features.ingredients.domain.repository;

import com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient;

import java.util.List;
import java.util.Set;

public interface IngredientsRepositoryInterface {
    Ingredient save(Ingredient ingredient);
    List<Ingredient> findAll();
    List<Ingredient> findAllById(Set<Long> ids);
}
