package com.h7.synapseai.app.dto;

import java.time.LocalDateTime;

public class SavedRecipeDto {

    private Long id;
    private String title;
    private LocalDateTime savedAt;

    public SavedRecipeDto(Long id, String title, LocalDateTime savedAt) {
        this.id = id;
        this.title = title;
        this.savedAt = savedAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}
