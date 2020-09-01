package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.exception.EntityNotFoundException;
import com.recipes.RecipeManagementBackend.model.*;
import com.recipes.RecipeManagementBackend.repository.RecipeIngredientRepository;
import com.recipes.RecipeManagementBackend.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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
        Recipe recipe = recipeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recipe " + id + " not found!"));

        String instructions = recipe.getInstructions();
        String newLine = "\n";
        String htmlBreak = "<br>";
        String htmlInstructions = instructions.replace(newLine, htmlBreak);

        recipe.setInstructions(htmlInstructions);

        return recipe;
    }

    public List<Recipe> getAllRecipesByUsername(String username) {
        return recipeRepository.findAllByUserUsername(username);
    }

    public List<Recipe> getAllRecipesByUserId(Long id) {
        return recipeRepository.findAllByUserId(id);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Transactional(noRollbackFor = EntityNotFoundException.class)
    public void saveRecipe(final RecipeTO recipe) {

        final Recipe recipeEntity = new Recipe();

        recipeEntity.setName(recipe.getName());
        recipeEntity.setInstructions(recipe.getInstructions());
        recipeEntity.setLink(recipe.getLink());
        recipeEntity.setUser(userService.getUserById(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName())));
        recipeEntity.setPictureLink(recipe.getPictureLink());

        Long recipeId = recipeRepository.save(recipeEntity).getId();

        for (RecipeIngredientTO ingredient : recipe.getIngredientBlock()) {
            final RecipeIngredient ingredients = new RecipeIngredient();
            ingredients.setQuantity(ingredient.getQuantity());
            ingredients.setRecipeId(recipeId);

            MeasurementUnit measurementUnitEntity = new MeasurementUnit();

            try {
                measurementUnitEntity = measurementUnitService.getMeasurementUnitByName(ingredient.getMeasurementUnit());
            } catch (EntityNotFoundException ignored) {
            }

            if (measurementUnitEntity.getName() == null) {
                measurementUnitEntity.setName(ingredient.getMeasurementUnit());
                measurementUnitEntity = measurementUnitService.saveMeasurementUnit(measurementUnitEntity);
            }
            ingredients.setMeasurementUnit(measurementUnitEntity);

            Ingredient ingredientEntity = new Ingredient();

            try {
                ingredientEntity = ingredientService.getIngredientByName(ingredient.getIngredient());
            } catch (EntityNotFoundException ignored) {
            }

            if (ingredientEntity.getName() == null) {
                ingredientEntity.setName(ingredient.getIngredient());
                ingredientEntity = ingredientService.saveIngredient(ingredientEntity);
            }
            ingredients.setIngredient(ingredientEntity);

            recipeIngredientRepository.save(ingredients);
        }
    }
}
