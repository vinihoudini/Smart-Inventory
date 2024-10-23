package com.example.inventory_management_api.service;

import com.example.inventory_management_api.model.Recipe;
import com.example.inventory_management_api.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    public void testCreateRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Pasta");
        recipe.setDescription("A simple pasta recipe");

        Mockito.when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        Recipe savedRecipe = recipeService.create(recipe);  // Corrigido: de createRecipe para create
        assertNotNull(savedRecipe);
        assertEquals("Pasta", savedRecipe.getName());
    }

    @Test
    public void testGetRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Salad");

        Mockito.when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        Recipe foundRecipe = recipeService.findById(1L);  // Corrigido: de getRecipeById para findById
        assertNotNull(foundRecipe);
        assertEquals("Salad", foundRecipe.getName());
    }

    @Test
    public void testUpdateRecipe() {
        Recipe existingRecipe = new Recipe();
        existingRecipe.setId(1L);
        existingRecipe.setName("Soup");

        Recipe updatedDetails = new Recipe();
        updatedDetails.setName("Updated Soup");

        Mockito.when(recipeRepository.findById(1L)).thenReturn(Optional.of(existingRecipe));
        Mockito.when(recipeRepository.save(any(Recipe.class))).thenReturn(existingRecipe);

        Recipe updatedRecipe = recipeService.update(updatedDetails);  // Corrigido: de updateRecipe para update
        assertEquals("Updated Soup", updatedRecipe.getName());
    }

    @Test
    public void testDeleteRecipe() {
        Mockito.doNothing().when(recipeRepository).deleteById(1L);

        assertDoesNotThrow(() -> recipeService.delete(1L));  // Corrigido: de deleteRecipe para delete
        Mockito.verify(recipeRepository, Mockito.times(1)).deleteById(1L);
    }
}
