package com.recipes.RecipeManagementBackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipes.RecipeManagementBackend.config.SecurityService;
import com.recipes.RecipeManagementBackend.model.Recipe;
import com.recipes.RecipeManagementBackend.model.RecipeIngredientView;
import com.recipes.RecipeManagementBackend.model.RecipeTO;
import com.recipes.RecipeManagementBackend.service.RecipeIngredientViewService;
import com.recipes.RecipeManagementBackend.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/rest/Recipe.svc")
public class RecipeController {
    private static Logger LOG = LoggerFactory.getLogger(RecipeController.class);

    private RecipeService recipeService;
    private RecipeIngredientViewService recipeIngredientViewService;
    private SecurityService securityService;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeIngredientViewService recipeIngredientViewService, SecurityService securityService) {
        this.recipeService = recipeService;
        this.recipeIngredientViewService = recipeIngredientViewService;
        this.securityService = securityService;
    }

    @GetMapping("/recipe/{id}")
    public Recipe getRecipeById(@PathVariable long id) {
        LOG.info("getRecipeById: " + id);
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/recipes/ingredients")
    public List<RecipeIngredientView> getAllRecipeIngredientCount(){
        LOG.info("getAllRecipeIngredientCounts");
        return recipeIngredientViewService.getAllRecipeIngredientCount();
    }

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        LOG.info("getAllRecipes");
        return recipeService.getAllRecipes();
    }

    @GetMapping("/my-recipes")
    public List<Recipe> getAllRecipesOfCurrentUser() {
        final long userId = securityService.getUserId();
        LOG.info("getAllRecipes for the logged user");
        return recipeService.getAllRecipesByUserId(userId);
    }

    @PostMapping("/recipe")
    public void saveRecipe(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RecipeTO recipe = objectMapper.readValue(json, RecipeTO.class);
        LOG.info("saveRecipe: " + recipe);
        recipeService.saveRecipe(recipe);
    }
}
