package com.fitness.meal_planner.features.recipes.data.mapper;

import com.fitness.meal_planner.features.recipes.data.model.RecipeModel;
import com.fitness.meal_planner.features.recipes.domain.entity.Recipe;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RecipeMapper {
    public Recipe toDomain(RecipeModel model) {
        return new Recipe(
                model.getId(),
                model.getName(),
                model.getServings(),
                model.getInstructions(),
                new Macronutrients(
                        model.getCaloriesPerServing(),
                        model.getProteinsPerServing(),
                        model.getCarbohydratesPerServing(),
                        model.getFatsPerServing(),
                        model.getFatsPerServing()
                ),
                Set.of()
        );
    }

    public RecipeModel toModel(Recipe domainEntity) {
        RecipeModel model = new RecipeModel();
        model.setName(domainEntity.getName());
        model.setServings(domainEntity.getServings());
        model.setInstructions(domainEntity.getInstructions());
        model.setCaloriesPerServing(domainEntity.getMacronutrients().calories());
        model.setProteinsPerServing(domainEntity.getMacronutrients().protein());
        model.setCarbohydratesPerServing(domainEntity.getMacronutrients().carbohydrates());
        model.setFatsPerServing(domainEntity.getMacronutrients().fats());
        model.setFibrePerServing(domainEntity.getMacronutrients().fibre());

        if (domainEntity.getId() != null) {
            model.setId(domainEntity.getId());
        }

        return model;
    }
}
