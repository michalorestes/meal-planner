package com.fitness.meal_planner.features.ingredients.application.service;

import com.fitness.meal_planner.features.ingredients.application.dto.IngredientDto;
import com.fitness.meal_planner.features.ingredients.application.mapper.IngredientMapper;
import com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient;
import com.fitness.meal_planner.features.ingredients.domain.repository.IngredientsRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class IngredientsProviderService {
    private final IngredientsRepositoryInterface ingredientsRepository;
    private final IngredientMapper ingredientMapper;

    public List<IngredientDto> getAllIngredients() {
        return ingredientsRepository
                .findAll()
                .stream()
                .map(ingredientMapper::toDto)
                .toList();
    }

    public List<Ingredient> getAllIngredientsByIds(Set<Long> ids) {
        return ingredientsRepository.findAllById(ids);
    }
}
