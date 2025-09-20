package com.fitness.meal_planner.features.recipes.data.mapper;

import com.fitness.meal_planner.features.recipes.data.model.RecipeModel;
import com.fitness.meal_planner.features.recipes.domain.entity.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {
    public Recipe toDomain(RecipeModel model) {
        return new Recipe(
                model.getId(),
                model.getName(),
                model.getServings(),
                model.getInstructions(),
                model.getCaloriesPerServing(),
                model.getProteinsPerServing(),
                model.getCarbohydratesPerServing(),
                model.getFatsPerServing(),
                model.getFatsPerServing()
        );
    }

    RecipeModel toModel(Recipe domainEntity) {
        RecipeModel model = new RecipeModel();
        model.setName(domainEntity.getName());
        model.setServings(domainEntity.getServings());
        model.setInstructions(domainEntity.getInstructions());
        model.setCaloriesPerServing(domainEntity.getCalories());
        model.setProteinsPerServing(domainEntity.getProtein());
        model.setCarbohydratesPerServing(domainEntity.getCarbohydrates());
        model.setFatsPerServing(domainEntity.getFats());
        model.setFibrePerServing(domainEntity.getFibre());

        if (domainEntity.getId() != null) {
            model.setId(domainEntity.getId());
        }

        return model;
    }
}
