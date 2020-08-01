package com.recipes.RecipeManagementBackend.repository;


import com.recipes.RecipeManagementBackend.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient,Long> {
}
