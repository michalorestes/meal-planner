package com.fitness.meal_planner.features.signup.domain.repository;

import java.util.Optional;

import com.fitness.meal_planner.features.signup.domain.entity.User;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user);
}
