package com.h7.synapseai.app.service;

import com.h7.synapseai.app.dto.RecipeRequest;
import com.h7.synapseai.app.dto.RecipeResponse;
import org.springframework.stereotype.Service;

@Service
public class RecipeGenerationService {

    private final HuggingFaceApiService huggingFaceApiService;

    public RecipeGenerationService(HuggingFaceApiService huggingFaceApiService) {
        this.huggingFaceApiService = huggingFaceApiService;
    }

    public RecipeResponse generateRecipe(RecipeRequest request) {
        return huggingFaceApiService.generateRecipe(request);
    }
}