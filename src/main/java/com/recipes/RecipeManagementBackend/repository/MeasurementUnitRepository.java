package com.recipes.RecipeManagementBackend.repository;

import com.recipes.RecipeManagementBackend.model.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit, Long> {
    Optional<MeasurementUnit> findByName(String name);
}
