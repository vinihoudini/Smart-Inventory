package com.example.inventory_management_api.service;

import com.example.inventory_management_api.model.Recipe;
import com.example.inventory_management_api.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe não encontrada"));
    }

    public Recipe create(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe update(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void delete(Long id) {
        recipeRepository.deleteById(id);
    }
}
