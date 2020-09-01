package com.recipes.RecipeManagementBackend.service;

import com.recipes.RecipeManagementBackend.model.*;
import com.recipes.RecipeManagementBackend.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Test
    void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setInstructions("Test instructions");
        Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(recipe));

        Recipe found = recipeService.getRecipeById(1L);

        assertThat(found.getId())
                .isEqualTo(recipe.getId());
    }

    @Test
    void getAllRecipesByUsername() {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        Recipe recipe3 = new Recipe();

        List<Recipe> recipeList = new ArrayList<>(Arrays.asList(recipe1, recipe2, recipe3));

        Mockito.when(recipeRepository.findAllByUserUsername(Mockito.anyString())).thenReturn(recipeList);

        List<Recipe> returnedRecipeList = new ArrayList<>(recipeService.getAllRecipesByUsername("testUser"));

        assertEquals(recipeList, returnedRecipeList);
    }

    @Test
    void getAllRecipes() {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();

        Mockito.when(recipeRepository.findAll()).thenReturn(Arrays.asList(recipe1, recipe2));

        List<Recipe> testList = recipeService.getAllRecipes();

        assertFalse(testList.isEmpty());
    }

}
