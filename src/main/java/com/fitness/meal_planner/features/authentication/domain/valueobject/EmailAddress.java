package com.fitness.meal_planner.features.authentication.domain.valueobject;


import jakarta.validation.constraints.Email;

public record EmailAddress(@Email String email) {
    
}
