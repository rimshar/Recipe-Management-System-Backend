package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.exception.EntityNotFoundException;
import com.recipes.RecipeManagementBackend.model.Recipe;
import com.recipes.RecipeManagementBackend.model.User;
import com.recipes.RecipeManagementBackend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recipe with Id" + id + " not found!"));
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }
}
