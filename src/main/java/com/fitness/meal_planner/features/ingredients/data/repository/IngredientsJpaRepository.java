package com.fitness.meal_planner.features.ingredients.data.repository;

import com.fitness.meal_planner.features.ingredients.data.model.IngredientModel;
import org.springframework.data.repository.CrudRepository;

public interface IngredientsJpaRepository extends CrudRepository<IngredientModel, Long> {

}
