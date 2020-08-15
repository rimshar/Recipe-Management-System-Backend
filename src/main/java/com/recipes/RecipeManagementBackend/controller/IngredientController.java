package com.recipes.RecipeManagementBackend.controller;

import com.recipes.RecipeManagementBackend.RecipeManagementBackendApplication;
import com.recipes.RecipeManagementBackend.model.Ingredient;
import com.recipes.RecipeManagementBackend.service.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/rest/Ingredient.svc")
@CrossOrigin(origins = "http://localhost:4200")
public class IngredientController {

    private static Logger LOG = LoggerFactory.getLogger(RecipeManagementBackendApplication.class);

    private IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredient/{id}")
    public Ingredient getIngredientById(@PathVariable Long id) {
        LOG.info("getIngredientById: " + id);
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return ingredient;
    }


    @GetMapping("/ingredients")
    public List<Ingredient> getAllIngredients() {
        LOG.info("getAllIngredients");
        return ingredientService.getAllIngredients();
    }

    @PostMapping("/ingredient")
    public void saveIngredient(@RequestBody Ingredient ingredient) {
        LOG.info("saveIngredient: " + ingredient);
        ingredientService.saveIngredient(ingredient);
    }

}
