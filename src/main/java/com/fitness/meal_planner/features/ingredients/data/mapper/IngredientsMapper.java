package com.fitness.meal_planner.features.ingredients.data.mapper;

import com.fitness.meal_planner.features.ingredients.data.model.IngredientModel;
import com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient;
import com.fitness.meal_planner.features.ingredients.domain.entity.IngredientType;
import com.fitness.meal_planner.features.ingredients.domain.entity.MeassurementUnit;

public class IngredientsMapper {
    private IngredientsMapper() {

    }

    public static Ingredient toDomainEntity(IngredientModel model) {
        return new Ingredient(
            model.getId(),
            model.getName(),
            IngredientType.fromString(model.getType()),
            model.getBrand(),
            MeassurementUnit.fromString(model.getMeasurementUnit()),
            model.getUnitSize(),
            model.getCaloriesPerUnit(),
            model.getProteinsPerUnit(),
            model.getCarbohydratesPerUnit(),
            model.getFatsPerUnit(),
            model.getFibrePerUnit(),
            model.getShopLink()
        );
    }

    public static IngredientModel toModel(Ingredient ingredient) {
        IngredientModel model = new IngredientModel();

        if (ingredient.getId() != null) {
            model.setId(ingredient.getId());
        }

        model.setName(ingredient.getName());
        model.setType(ingredient.getType().toString());
        model.setBrand(ingredient.getBrand());
        model.setMeasurementUnit(ingredient.getMeassurementUnit().toString());
        model.setUnitSize(ingredient.getUnitSize());
        model.setCaloriesPerUnit(ingredient.getCaloriesPerUnit());
        model.setProteinsPerUnit(ingredient.getProteinsPerUnit());
        model.setCarbohydratesPerUnit(ingredient.getCarbohydratesPerUnit());
        model.setFatsPerUnit(ingredient.getFatsPerUnit());
        model.setFibrePerUnit(ingredient.getFibrePerUnit());
        model.setShopLink(ingredient.getShopLink());

        return model;
    }
}
