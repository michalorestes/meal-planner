package com.fitness.meal_planner.features.authentication.application.dto;

public record CreateUserCommand(String email, String username, String password) {
}
