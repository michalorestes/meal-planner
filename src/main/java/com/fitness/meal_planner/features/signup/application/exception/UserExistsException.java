package com.fitness.meal_planner.features.signup.application.exception;

public class UserExistsException extends Exception {
    public UserExistsException(String email) {
        super("User with email " + email + " already exists");
    }
}
