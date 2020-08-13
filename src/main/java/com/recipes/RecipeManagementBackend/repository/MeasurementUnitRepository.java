package com.recipes.RecipeManagementBackend.repository;

import com.recipes.RecipeManagementBackend.model.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit, Long> {
    Optional<MeasurementUnit> findByName(String name);
}
