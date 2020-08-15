package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.exception.EntityNotFoundException;
import com.recipes.RecipeManagementBackend.model.*;
import com.recipes.RecipeManagementBackend.repository.RecipeIngredientRepository;
import com.recipes.RecipeManagementBackend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RecipeService {

    private RecipeRepository recipeRepository;
    private UserService userService;
    private RecipeIngredientRepository recipeIngredientRepository;
    private MeasurementUnitService measurementUnitService;
    private IngredientService ingredientService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserService userService, RecipeIngredientRepository recipeIngredientRepository,
                         MeasurementUnitService measurementUnitService, IngredientService ingredientService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.measurementUnitService = measurementUnitService;
        this.ingredientService = ingredientService;
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recipe " + id + " not found!"));
    }

    public List<Recipe> getAllRecipesByUsername(String username) {
        return recipeRepository.findAllByUserUsername(username);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveRecipe(final RecipeTO recipe) {

        final Recipe recipeEntity = new Recipe();
        recipeEntity.setName(recipe.getName());
        recipeEntity.setInstructions(recipe.getInstructions());
        recipeEntity.setLink(recipe.getLink());
        recipeEntity.setUser(userService.getUserById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())));
        Long recipeId = recipeRepository.save(recipeEntity).getId();

        for (RecipeIngredientTO ingredient : recipe.getIngredientBlock()) {
            final RecipeIngredient ingredients = new RecipeIngredient();
            ingredients.setQuantity(ingredient.getQuantity());
            ingredients.setRecipeId(recipeId);
            // ingredients.setRecipe(recipeRepository.getOne(recipeId));


            try {
                MeasurementUnit measurementUnitEntity = measurementUnitService.getMeasurementUnitByName(ingredient.getMeasurementUnit());
                ingredients.setMeasurementUnit(measurementUnitEntity);
            } catch (EntityNotFoundException exception) {
                MeasurementUnit measurementUnitEntity = new MeasurementUnit();
                measurementUnitEntity.setName(ingredient.getMeasurementUnit());
                measurementUnitEntity = measurementUnitService.saveMeasurementUnit(measurementUnitEntity);
                ingredients.setMeasurementUnit(measurementUnitEntity);
            }

            try {
                Ingredient ingredientEntity = ingredientService.getIngredientByName(ingredient.getIngredient());
                ingredients.setIngredient(ingredientEntity);
            } catch (EntityNotFoundException exception) {
                Ingredient ingredientEntity = new Ingredient();
                ingredientEntity.setName(ingredient.getIngredient());
                ingredientEntity = ingredientService.saveIngredient(ingredientEntity);
                ingredients.setIngredient(ingredientEntity);
            }

            recipeIngredientRepository.save(ingredients);
        }
    }
}
