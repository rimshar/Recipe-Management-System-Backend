package com.recipes.RecipeManagementBackend.repository;

import com.recipes.RecipeManagementBackend.model.RecipeIngredientView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientViewRepository extends JpaRepository<RecipeIngredientView, Long> {

}
