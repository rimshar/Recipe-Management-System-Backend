package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.exception.EntityNotFoundException;
import com.recipes.RecipeManagementBackend.model.IngredientMeasurementUnit;
import com.recipes.RecipeManagementBackend.model.MeasurementUnit;
import com.recipes.RecipeManagementBackend.repository.IngredientMeasurementUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientMeasurementUnitService {

    private IngredientMeasurementUnitRepository ingredientMeasurementUnitRepository;

    @Autowired
    public IngredientMeasurementUnitService(IngredientMeasurementUnitRepository ingredientMeasurementUnitRepository) {
        this.ingredientMeasurementUnitRepository = ingredientMeasurementUnitRepository;
    }

    public IngredientMeasurementUnit getIngredientMeasurementUnitById(Long id) {
        return ingredientMeasurementUnitRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Ingredient Measurement Unit " + id + " not found!"));
    }

    public List<IngredientMeasurementUnit> getAllIngredientMeasurementUnits(){
        return ingredientMeasurementUnitRepository.findAll();
    }

    @Transactional
    public void saveIngredientMeasurementUnit(IngredientMeasurementUnit ingredientMeasurementUnit){
        ingredientMeasurementUnitRepository.save(ingredientMeasurementUnit);
    }

    public List<MeasurementUnit> getAllMeasurementUnitsForIngredient(Long id){
        List<IngredientMeasurementUnit> ingredientMeasurementUnits = ingredientMeasurementUnitRepository.findAllByIngredientId(id);
        return ingredientMeasurementUnits.stream().map(IngredientMeasurementUnit::getMeasurementUnit).collect(Collectors.toList());
    }

}
