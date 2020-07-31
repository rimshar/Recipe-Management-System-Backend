package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.model.Recipe;
import com.recipes.RecipeManagementBackend.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/rest/Recipe.svc")
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {
    private static Logger LOG = LoggerFactory.getLogger(RecipeController.class);

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        LOG.info("getRecipeById: " + id);
        Recipe recipe = recipeService.getRecipeById(id);
        return recipe;
    }

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        LOG.info("getAllRecipes");
        return recipeService.getAllRecipes();
    }

    @PostMapping("/recipe")
    public void saveRecipe(@RequestBody Recipe recipe) {
        LOG.info("saveRecipe: " + recipe);
        recipeService.saveRecipe(recipe);
    }
}
