package com.fitness.meal_planner.features.authentication.domain.valueobject;

public record UserId(Long userId) {
    public UserId {
        if (userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }

        if (userId <= 0) {
            throw new IllegalArgumentException("UserId must be a positive number");
        }
    }
}
