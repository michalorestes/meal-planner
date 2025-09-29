package com.fitness.meal_planner.features.ingredients.data.mapper;

import com.fitness.meal_planner.features.ingredients.data.model.IngredientModel;
import com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient;
import com.fitness.meal_planner.features.ingredients.domain.entity.IngredientType;
import com.fitness.meal_planner.features.ingredients.domain.entity.MeassurementUnit;
import com.fitness.meal_planner.shared.valueobject.Macronutrients;
import org.springframework.stereotype.Component;

@Component
public class IngredientsMapper {
    private IngredientsMapper() {

    }

    public Ingredient toDomainEntity(IngredientModel model) {
        return new Ingredient(
            model.getId(),
            model.getName(),
            IngredientType.fromString(model.getType()),
            model.getBrand(),
            MeassurementUnit.fromString(model.getMeasurementUnit()),
            model.getUnitSize(),
            new Macronutrients(
                    model.getCaloriesPerUnit(),
                    model.getProteinsPerUnit(),
                    model.getCarbohydratesPerUnit(),
                    model.getFatsPerUnit(),
                    model.getFibrePerUnit()
            ),
            model.getShopLink()
        );
    }

    public IngredientModel toModel(Ingredient ingredient) {
        IngredientModel model = new IngredientModel();

        if (ingredient.getId() != null) {
            model.setId(ingredient.getId());
        }

        model.setName(ingredient.getName());
        model.setType(ingredient.getType().toString());
        model.setBrand(ingredient.getBrand());
        model.setMeasurementUnit(ingredient.getMeassurementUnit().toString());
        model.setUnitSize(ingredient.getUnitSize());
        model.setCaloriesPerUnit(ingredient.getMacronutrients().calories());
        model.setProteinsPerUnit(ingredient.getMacronutrients().protein());
        model.setCarbohydratesPerUnit(ingredient.getMacronutrients().carbohydrates());
        model.setFatsPerUnit(ingredient.getMacronutrients().fats());
        model.setFibrePerUnit(ingredient.getMacronutrients().fibre());
        model.setShopLink(ingredient.getShopLink());

        return model;
    }
}
