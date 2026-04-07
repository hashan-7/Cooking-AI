package com.h7.synapseai.app.dto;

import java.util.List;

public class RecipeResponse {

    private String title;
    private String description;
    private List<String> ingredients;
    private List<String> steps;
    private List<String> tips;
    private String recipeContent;

    public RecipeResponse() {
    }

    public RecipeResponse(String title, String description, List<String> ingredients, List<String> steps, List<String> tips, String recipeContent) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
        this.tips = tips;
        this.recipeContent = recipeContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public List<String> getTips() {
        return tips;
    }

    public void setTips(List<String> tips) {
        this.tips = tips;
    }

    public String getRecipeContent() {
        return recipeContent;
    }

    public void setRecipeContent(String recipeContent) {
        this.recipeContent = recipeContent;
    }
}