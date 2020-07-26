package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
}
