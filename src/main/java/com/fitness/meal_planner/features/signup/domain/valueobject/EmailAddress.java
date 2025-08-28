package com.fitness.meal_planner.features.signup.domain.valueobject;


import jakarta.validation.constraints.Email;

public record EmailAddress(@Email String email) {
    
}
