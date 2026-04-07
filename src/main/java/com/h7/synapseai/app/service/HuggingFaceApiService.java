package com.h7.synapseai.app.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.h7.synapseai.app.dto.RecipeRequest;
import com.h7.synapseai.app.dto.RecipeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class HuggingFaceApiService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Value("${huggingface.api.url}")
    private String apiUrl;

    public HuggingFaceApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = new ObjectMapper();
    }

    public RecipeResponse generateRecipe(RecipeRequest request) {
        if (request == null || request.getIngredients() == null || request.getIngredients().isEmpty()) {
            return buildFallbackResponse("Please provide at least one ingredient.");
        }

        try {
            Map<String, Object> payload = new LinkedHashMap<>();
            payload.put("ingredients", request.getIngredients());
            payload.put("cuisine", request.getCuisine());
            payload.put("diet", request.getDiet());
            payload.put("cookTime", request.getCookTime());

            String responseBody = webClient.post()
                    .uri(apiUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(payload)
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(60))
                    .block();

            return parseResponse(responseBody);

        } catch (Exception e) {
            System.err.println("SPACE API ERROR: " + e.getMessage());
            return buildFallbackResponse("Recipe generation is currently unavailable. Please try again in a moment.");
        }
    }

    private RecipeResponse parseResponse(String responseBody) throws Exception {
        if (responseBody == null || responseBody.isBlank()) {
            return buildFallbackResponse("Recipe generation is currently unavailable. Please try again in a moment.");
        }

        JsonNode root = objectMapper.readTree(responseBody);

        if (root.has("detail")) {
            System.err.println("SPACE API DETAIL: " + root.get("detail").asText());
            return buildFallbackResponse("Recipe generation is currently unavailable. Please try again in a moment.");
        }

        String title = getText(root, "title");
        String description = getText(root, "description");
        String recipeContent = getText(root, "recipeContent");

        List<String> ingredients = getTextList(root, "ingredients");
        List<String> steps = getTextList(root, "steps");
        List<String> tips = getTextList(root, "tips");

        if (recipeContent == null || recipeContent.isBlank()) {
            recipeContent = buildRecipeContent(title, description, ingredients, steps, tips);
        }

        return new RecipeResponse(
                title,
                description,
                ingredients,
                steps,
                tips,
                recipeContent
        );
    }

    private String getText(JsonNode root, String fieldName) {
        if (root.has(fieldName) && !root.get(fieldName).isNull()) {
            return root.get(fieldName).asText();
        }
        return "";
    }

    private List<String> getTextList(JsonNode root, String fieldName) {
        List<String> values = new ArrayList<>();

        if (root.has(fieldName) && root.get(fieldName).isArray()) {
            for (JsonNode item : root.get(fieldName)) {
                values.add(item.asText());
            }
        }

        return values;
    }

    private String buildRecipeContent(String title, String description, List<String> ingredients, List<String> steps, List<String> tips) {
        StringBuilder builder = new StringBuilder();

        builder.append("Title: ").append(title == null ? "" : title).append("\n\n");
        builder.append("Short Description: ").append(description == null ? "" : description).append("\n\n");

        builder.append("Ingredients:\n");
        for (String item : ingredients) {
            builder.append("- ").append(item).append("\n");
        }

        builder.append("\nSteps:\n");
        for (int i = 0; i < steps.size(); i++) {
            builder.append(i + 1).append(". ").append(steps.get(i)).append("\n");
        }

        builder.append("\nTips:\n");
        for (String tip : tips) {
            builder.append("- ").append(tip).append("\n");
        }

        return builder.toString().trim();
    }

    private RecipeResponse buildFallbackResponse(String message) {
        List<String> tips = new ArrayList<>();
        tips.add(message);

        return new RecipeResponse(
                "Recipe Unavailable",
                message,
                new ArrayList<>(),
                new ArrayList<>(),
                tips,
                message
        );
    }
}