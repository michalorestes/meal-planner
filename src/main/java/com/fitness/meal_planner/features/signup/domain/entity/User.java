package com.fitness.meal_planner.features.signup.domain.entity;

import com.fitness.meal_planner.features.signup.domain.valueobject.EmailAddress;
import com.fitness.meal_planner.features.signup.domain.valueobject.Password;
import com.fitness.meal_planner.features.signup.domain.valueobject.UserId;
import com.fitness.meal_planner.features.signup.domain.valueobject.Username;

import java.time.LocalDateTime;

public record User(
        UserId id,
        Username username,
        EmailAddress email,
        Password passwordHash,
        LocalDateTime createdAt
) {
}
