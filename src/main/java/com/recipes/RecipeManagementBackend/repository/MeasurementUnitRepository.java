package com.recipes.RecipeManagementBackend.repository;

import com.recipes.RecipeManagementBackend.model.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit, Long> {
}
