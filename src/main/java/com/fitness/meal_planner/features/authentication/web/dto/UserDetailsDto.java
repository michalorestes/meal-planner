package com.fitness.meal_planner.features.authentication.web.dto;

public record UserDetailsDto(
    String username,
    String email,
    String password
) {
    
}
