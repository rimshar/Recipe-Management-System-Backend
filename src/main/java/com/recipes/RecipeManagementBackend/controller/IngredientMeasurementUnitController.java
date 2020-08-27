package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.model.IngredientMeasurementUnit;
import com.recipes.RecipeManagementBackend.model.MeasurementUnit;
import com.recipes.RecipeManagementBackend.service.IngredientMeasurementUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("/api/rest/IngredientMeasurementUser.svc")
public class IngredientMeasurementUnitController {
    private static Logger LOG
            = LoggerFactory.getLogger(UserController.class);

    private IngredientMeasurementUnitService ingredientMeasurementUnitService;

    @Autowired
    public IngredientMeasurementUnitController(IngredientMeasurementUnitService ingredientMeasurementUnitService){
        this.ingredientMeasurementUnitService = ingredientMeasurementUnitService;
    }

    @GetMapping("/ingredient-measurement-unit/{id}")
    public IngredientMeasurementUnit getIngredientMeasurementUnitById(@PathVariable long id) {
        LOG.info("getIngredientMeasurementUnitById: " + id);
        return ingredientMeasurementUnitService.getIngredientMeasurementUnitById(id);
    }

    @GetMapping("/ingredient-measurement-units")
    public List<IngredientMeasurementUnit> getAllIngredientMeasurementUnits() {
        LOG.info("getAllIngredientMeasurementUnits");
        return ingredientMeasurementUnitService.getAllIngredientMeasurementUnits();
    }

    @GetMapping("/ingredient-measurement-units/ingredient/{id}")
    public List<MeasurementUnit> getMeasurementUnitsByIngredient(@PathVariable long id){
        LOG.info("getMeasurementUnitsByIngredient");
        return ingredientMeasurementUnitService.getAllMeasurementUnitsForIngredient(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/ingredient-measurement-unit")
    public void saveIngredientMeasurementUnit(@RequestBody IngredientMeasurementUnit ingredientMeasurementUnit) {
        LOG.info("saveIngredientMeasurementUnit: " + ingredientMeasurementUnit);
        ingredientMeasurementUnitService.saveIngredientMeasurementUnit(ingredientMeasurementUnit);
    }
}
