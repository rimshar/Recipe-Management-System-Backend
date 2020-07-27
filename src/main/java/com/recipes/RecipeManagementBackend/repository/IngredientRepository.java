package com.recipes.RecipeManagementBackend.repository;

import com.recipes.RecipeManagementBackend.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
