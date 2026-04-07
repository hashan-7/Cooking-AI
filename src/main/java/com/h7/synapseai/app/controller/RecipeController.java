package com.h7.synapseai.app.controller;

import com.h7.synapseai.app.dto.RecipeRequest;
import com.h7.synapseai.app.dto.RecipeResponse;
import com.h7.synapseai.app.service.IngredientService;
import com.h7.synapseai.app.service.RecipeGenerationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeGenerationService recipeGenerationService;
    private final IngredientService ingredientService;

    public RecipeController(RecipeGenerationService recipeGenerationService, IngredientService ingredientService) {
        this.recipeGenerationService = recipeGenerationService;
        this.ingredientService = ingredientService;
    }

    @PostMapping("/generate")
    public RecipeResponse generateRecipe(@RequestBody RecipeRequest request) {
        return recipeGenerationService.generateRecipe(request);
    }

    @GetMapping("/surprise")
    public RecipeResponse surpriseMe() {
        RecipeRequest surpriseRequest = new RecipeRequest();
        surpriseRequest.setIngredients(ingredientService.getSurpriseIngredients());
        return recipeGenerationService.generateRecipe(surpriseRequest);
    }
}