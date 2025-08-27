package com.fitness.meal_planner.features.signup.domain.entity;

import java.time.LocalDateTime;

public record User(
        Long id,
        String username,
        String email,
        String passwordHash,
        LocalDateTime createdAt
) {
}
