package com.recipes.RecipeManagementBackend.repository;

import com.recipes.RecipeManagementBackend.model.IngredientMeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientMeasurementUnitRepository extends JpaRepository<IngredientMeasurementUnit, Long> {

    public List<IngredientMeasurementUnit> findAllByIngredientId(Long id);

}
