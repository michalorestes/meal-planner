package com.fitness.meal_planner.features.recipes.data.repository;

import com.fitness.meal_planner.features.recipes.data.model.RecipeModel;
import org.springframework.data.repository.CrudRepository;

public interface RecipeJpaRepository extends CrudRepository<RecipeModel, Long> {
}
