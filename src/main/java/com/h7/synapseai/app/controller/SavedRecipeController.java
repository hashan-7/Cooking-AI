package com.h7.synapseai.app.controller;

import com.h7.synapseai.app.dto.RecipeDetailDto;
import com.h7.synapseai.app.dto.SaveRecipeRequest;
import com.h7.synapseai.app.dto.SavedRecipeDto;
import com.h7.synapseai.app.model.Recipe;
import com.h7.synapseai.app.model.User;
import com.h7.synapseai.app.repository.RecipeRepository;
import com.h7.synapseai.app.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/saved-recipes")
public class SavedRecipeController {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public SavedRecipeController(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<RecipeDetailDto> saveRecipe(@RequestBody SaveRecipeRequest saveRecipeRequest,
                                                      @AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String googleId = oauth2User.getAttribute("sub");
        Optional<User> userOptional = userRepository.findByGoogleId(googleId);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userOptional.get();

        Recipe recipe = new Recipe();
        recipe.setTitle(saveRecipeRequest.getTitle());
        recipe.setContent(saveRecipeRequest.getContent());
        recipe.setUser(user);
        recipe.setSavedAt(LocalDateTime.now());

        Recipe savedRecipe = recipeRepository.save(recipe);

        RecipeDetailDto response = new RecipeDetailDto(
                savedRecipe.getId(),
                savedRecipe.getTitle(),
                savedRecipe.getContent()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SavedRecipeDto>> getSavedRecipes(@AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String googleId = oauth2User.getAttribute("sub");
        Optional<User> userOptional = userRepository.findByGoogleId(googleId);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userOptional.get();
        List<Recipe> recipes = recipeRepository.findByUserOrderBySavedAtDesc(user);

        List<SavedRecipeDto> recipeDtos = recipes.stream()
                .map(recipe -> new SavedRecipeDto(recipe.getId(), recipe.getTitle(), recipe.getSavedAt()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(recipeDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDetailDto> getRecipeById(@PathVariable Long id,
                                                         @AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String googleId = oauth2User.getAttribute("sub");

        return recipeRepository.findByIdAndUser_GoogleId(id, googleId)
                .map(recipe -> ResponseEntity.ok(
                        new RecipeDetailDto(
                                recipe.getId(),
                                recipe.getTitle(),
                                recipe.getContent()
                        )
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRecipe(@PathVariable Long id,
                                               @AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String googleId = oauth2User.getAttribute("sub");

        return recipeRepository.findByIdAndUser_GoogleId(id, googleId)
                .map(recipe -> {
                    recipeRepository.delete(recipe);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}