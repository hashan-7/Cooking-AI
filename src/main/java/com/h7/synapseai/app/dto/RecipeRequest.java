package com.h7.synapseai.app.dto;

import java.util.List;

public class RecipeRequest {

    private List<String> ingredients;
    private String cuisine; // e.g., "Italian", "Chinese"
    private String diet; // e.g., "Vegetarian", "Vegan"
    private String cookTime; // e.g., "Under 30 minutes"

    // Getters and Setters

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }
}
