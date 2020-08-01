package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.model.Ingredient;
import com.recipes.RecipeManagementBackend.model.IngredientMeasurementUnit;
import com.recipes.RecipeManagementBackend.model.MeasurementUnit;
import com.recipes.RecipeManagementBackend.service.IngredientMeasurementUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/rest/IngredientMeasurementUser.svc")
@CrossOrigin(origins = "http://localhost:4200")
public class IngredientMeasurementUnitController {
    private static Logger LOG
            = LoggerFactory.getLogger(UserController.class);

    private IngredientMeasurementUnitService ingredientMeasurementUnitService;

    @Autowired
    public IngredientMeasurementUnitController(IngredientMeasurementUnitService ingredientMeasurementUnitService){
        this.ingredientMeasurementUnitService = ingredientMeasurementUnitService;
    }

    @GetMapping("/ingredient-measurement-unit/{id}")
    public IngredientMeasurementUnit getIngredientMeasurementUnitById(@PathVariable Long id) {
        LOG.info("getIngredientMeasurementUnitById: " + id);
        IngredientMeasurementUnit ingredientMeasurementUnit = ingredientMeasurementUnitService.getIngredientMeasurementUnitById(id);
        return ingredientMeasurementUnit;
    }

    @GetMapping("/ingredient-measurement-units")
    public List<IngredientMeasurementUnit> getAllIngredientMeasurementUnits() {
        LOG.info("getAllIngredientMeasurementUnits");
        return ingredientMeasurementUnitService.getAllIngredientMeasurementUnits();
    }

    @GetMapping("/ingredient-measurement-units/ingredient/{id}")
    public List<MeasurementUnit> getMeasurementUnitsByIngredient(@PathVariable Long id){
        LOG.info("getMeasurementUnitsByIngredient");
        return ingredientMeasurementUnitService.getAllMeasurementUnitsForIngredient(id);
    }

    @PostMapping("/ingredient-measurement-unit")
    public void saveIngredientMeasurementUnit(@RequestBody IngredientMeasurementUnit ingredientMeasurementUnit) {
        LOG.info("saveIngredientMeasurementUnit: " + ingredientMeasurementUnit);
        ingredientMeasurementUnitService.saveIngredientMeasurementUnit(ingredientMeasurementUnit);
    }
}
