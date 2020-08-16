package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.model.RecipeIngredientView;
import com.recipes.RecipeManagementBackend.repository.RecipeIngredientViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeIngredientViewService {

    private RecipeIngredientViewRepository recipeIngredientViewRepository;

    @Autowired
    public RecipeIngredientViewService(RecipeIngredientViewRepository recipeIngredientViewRepository) {
        this.recipeIngredientViewRepository = recipeIngredientViewRepository;
    }

    public List<RecipeIngredientView> getAllRecipeIngredientCount() {
        return recipeIngredientViewRepository.findAll();
    }

}
