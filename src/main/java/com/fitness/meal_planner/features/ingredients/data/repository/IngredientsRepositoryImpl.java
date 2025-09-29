package com.fitness.meal_planner.features.ingredients.data.repository;

import com.fitness.meal_planner.features.ingredients.data.mapper.IngredientsMapper;
import com.fitness.meal_planner.features.ingredients.data.model.IngredientModel;
import com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient;
import com.fitness.meal_planner.features.ingredients.domain.repository.IngredientsRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class IngredientsRepositoryImpl implements IngredientsRepositoryInterface {

    private final IngredientsJpaRepository jpaRepository;
    private final IngredientsMapper mapper;

    @Override
    public Ingredient save(Ingredient ingredient) {
        IngredientModel model = mapper.toModel(ingredient);
        IngredientModel savedModel = jpaRepository.save(model);

        return mapper.toDomainEntity(savedModel);
    }

    public List<Ingredient> findAll() {
        List<IngredientModel> models = new ArrayList<>();
        jpaRepository.findAll().forEach(models::add);

        return models.stream()
                .map(mapper::toDomainEntity)
                .toList();
    }
}
