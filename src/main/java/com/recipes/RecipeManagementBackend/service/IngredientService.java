package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.exception.EntityNotFoundException;
import com.recipes.RecipeManagementBackend.model.Ingredient;
import com.recipes.RecipeManagementBackend.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngredientService {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient getIngredientById(Long id){
        return ingredientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Ingredient " + id + " not found!"));
    }

    public Ingredient getIngredientByName(String name){
        return ingredientRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("Ingredient " + name + " not found!"));
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Transactional
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

}
