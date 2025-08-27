package com.fitness.meal_planner.features.signup.domain.repository;

import com.fitness.meal_planner.features.signup.domain.entity.User;

public interface UserRepository {
    User findByEmail(String email);
    User save(User user);
}
