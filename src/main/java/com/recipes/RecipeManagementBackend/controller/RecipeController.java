package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.model.Recipe;
import com.recipes.RecipeManagementBackend.model.User;
import com.recipes.RecipeManagementBackend.service.RecipeService;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rest/Recipe.svc")
public class RecipeController {

    private static Logger LOG = LoggerFactory.getLogger(Application.class);

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
