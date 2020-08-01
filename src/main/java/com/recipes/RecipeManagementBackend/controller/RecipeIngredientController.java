package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.model.RecipeIngredient;
import com.recipes.RecipeManagementBackend.service.RecipeIngredientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/rest/RecipeIngredient.svc")
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeIngredientController {
    private static Logger LOG = LoggerFactory.getLogger(RecipeIngredientController.class);
    private RecipeIngredientService recipeIngredientService;

    @Autowired
    public RecipeIngredientController(RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }

    @PostMapping("/recipeIngredient")
    public void saveRecipeIngredient(@RequestBody RecipeIngredient recipeIngredient) {
        LOG.info("saveRecipeIngredient: " + recipeIngredient);
        recipeIngredientService.saveRecipeIngredient(recipeIngredient);
    }

    @GetMapping("/recipeIngredient/{id}")
    public RecipeIngredient getRecipeById(@PathVariable Long id) {
        LOG.info("getRecipeIngredientById: " + id);
        RecipeIngredient recipeIngredient = recipeIngredientService.getRecipeIngredientById(id);
        return recipeIngredient;
    }

    @GetMapping("/recipeIngredient")
    public List<RecipeIngredient> getAllRecipes() {
        LOG.info("getAllRecipesIngredient");
        return recipeIngredientService.getAllRecipesIngredient();
    }
}