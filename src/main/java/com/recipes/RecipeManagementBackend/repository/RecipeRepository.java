package com.recipes.RecipeManagementBackend.repository;

import com.recipes.RecipeManagementBackend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository
        extends JpaRepository<Recipe, Long> {
}
