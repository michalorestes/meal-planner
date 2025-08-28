package com.fitness.meal_planner.features.signup.domain.entity;

import com.fitness.meal_planner.features.signup.domain.valueobject.EmailAddress;
import com.fitness.meal_planner.features.signup.domain.valueobject.PasswordHashed;
import com.fitness.meal_planner.features.signup.domain.valueobject.UserId;
import com.fitness.meal_planner.features.signup.domain.valueobject.Username;

import java.time.LocalDateTime;

public record User(
        UserId id,
        Username username,
        EmailAddress email,
        PasswordHashed passwordHashed,
        LocalDateTime createdAt
) {
}
