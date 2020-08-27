package com.recipes.RecipeManagementBackend.repository;

import com.recipes.RecipeManagementBackend.model.IngredientMeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientMeasurementUnitRepository extends JpaRepository<IngredientMeasurementUnit, Long> {

    List<IngredientMeasurementUnit> findAllByIngredientId(Long id);

}
