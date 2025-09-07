package com.fitness.meal_planner.features.authentication.domain.valueobject;

import jakarta.validation.constraints.NotBlank;

public record Username(@NotBlank String username) {
    
}
