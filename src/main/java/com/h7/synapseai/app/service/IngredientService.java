package com.h7.synapseai.app.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private final List<String> allIngredients = List.of(
            "Chicken Breast", "Rice", "Carrot", "Onion", "Garlic", "Tomato", "Potato",
            "Broccoli", "Bell Pepper", "Spinach", "Mushroom", "Cheese", "Egg", "Milk",
            "Flour", "Sugar", "Salt", "Black Pepper", "Olive Oil", "Soy Sauce", "Beef",
            "Pork", "Fish", "Shrimp", "Pasta", "Bread", "Butter", "Lemon", "Lime",
            "Cilantro", "Basil", "Oregano", "Thyme", "Rosemary", "Chili Powder",
            "Cumin", "Paprika", "Ginger", "Turmeric", "Cinnamon", "Nutmeg", "Honey",
            "Maple Syrup", "Vinegar", "Mustard", "Ketchup", "Mayonnaise", "Yogurt",
            "Coconut Milk", "Almonds", "Walnuts", "Peanuts", "Oats", "Quinoa",
            "Lentils", "Chickpeas", "Black Beans", "Kidney Beans", "Corn", "Peas",
            "Salmon Fillet", "Ground Beef", "Bacon", "Sausage", "Tofu", "Tempeh",
            "Cheddar Cheese", "Mozzarella Cheese", "Parmesan Cheese", "Feta Cheese",
            "Avocado", "Cucumber", "Lettuce", "Zucchini", "Eggplant", "Sweet Potato",
            "Celery", "Cabbage", "Cauliflower", "Asparagus", "Green Beans", "Apple",
            "Banana", "Orange", "Grapes", "Strawberries", "Blueberries", "Raspberries"
    );

    public List<String> searchIngredients(String query) {
        if (query == null || query.trim().isEmpty()) {
            return List.of();
        }
        String lowerCaseQuery = query.toLowerCase();
        return allIngredients.stream()
                .filter(ingredient -> ingredient.toLowerCase().contains(lowerCaseQuery))
                .limit(10) // We will return a maximum of 10 suggestions
                .collect(Collectors.toList());
    }

    public List<String> getSurpriseIngredients() {
        List<String> shuffledIngredients = new ArrayList<>(allIngredients);
        Collections.shuffle(shuffledIngredients);
        // Return a sublist of 3 to 5 random ingredients
        int numberOfIngredients = 3 + (int)(Math.random() * 3); // Random number between 3 and 5
        return shuffledIngredients.subList(0, numberOfIngredients);
    }
}
