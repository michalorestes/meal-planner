package com.fitness.meal_planner.features.ingredients.data;

import com.fitness.meal_planner.features.ingredients.data.model.IngredientModel;
import com.fitness.meal_planner.features.ingredients.data.repository.IngredientsRepositoryImpl;
import com.fitness.meal_planner.features.ingredients.domain.entity.Ingredient;
import com.fitness.meal_planner.features.ingredients.domain.entity.IngredientType;
import com.fitness.meal_planner.features.ingredients.domain.entity.MeassurementUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(IngredientsRepositoryImpl.class)
public class IngredientRepositoryImplTest {

    @Autowired
    private IngredientsRepositoryImpl repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testCanSaveIngredient() {
        Ingredient ingredient = new Ingredient(
            null,
            "Chicken Breast",
            IngredientType.PROTEIN,
            "Brand A",
            MeassurementUnit.GRAMS,
            100.0,
            165,
            31.0,
            0.0,
            3.6,
            0.0,
            "http://example.com/chicken"
        );

        Ingredient ingredientSaved = repository.save(ingredient);

        IngredientModel model = entityManager.find(IngredientModel.class, ingredientSaved.getId());
        Assertions.assertEquals(model.getName(), ingredientSaved.getName());
    }

    @Test
    void testCanFetchAllIngredients() {
        IngredientModel carrot = new IngredientModel();
        carrot.setName("Carrot");
        carrot.setType(IngredientType.VEGETABLE.getType());
        carrot.setBrand("Brand B");
        carrot.setMeasurementUnit(MeassurementUnit.GRAMS.getUnit());
        carrot.setUnitSize(100.0);
        carrot.setCaloriesPerUnit(41);
        carrot.setProteinsPerUnit(0.9);
        carrot.setCarbohydratesPerUnit(9.6);
        carrot.setFatsPerUnit(0.2);
        carrot.setFibrePerUnit(2.8);
        carrot.setShopLink("http://example.com/carrot");
        entityManager.persist(carrot);

        IngredientModel rice = new IngredientModel();
        rice.setName("Rice");
        rice.setType(IngredientType.GRAINS.getType());
        rice.setBrand("Brand C");
        rice.setMeasurementUnit(MeassurementUnit.GRAMS.getUnit());
        rice.setUnitSize(100.0);
        rice.setCaloriesPerUnit(130);
        rice.setProteinsPerUnit(2.7);
        rice.setCarbohydratesPerUnit(28.0);
        rice.setFatsPerUnit(0.3);
        rice.setFibrePerUnit(0.4);
        rice.setShopLink("http://example.com/rice");
        entityManager.persist(rice);
        entityManager.flush();

        List<Ingredient> ingredients = repository.findAll();

        Assertions.assertEquals(2, ingredients.size());
        Assertions.assertTrue(ingredients.stream().anyMatch(i -> i.getName().equals("Carrot")));
        Assertions.assertTrue(ingredients.stream().anyMatch(i -> i.getName().equals("Rice")));
    }

    @Test
    void testHandlesEmptyResults() {
        List<Ingredient> ingredients = repository.findAll();
        Assertions.assertEquals(0, ingredients.size());
    }
}
