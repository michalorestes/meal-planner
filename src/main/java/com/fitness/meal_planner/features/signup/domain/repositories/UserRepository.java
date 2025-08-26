package com.fitness.meal_planner.features.signup.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fitness.meal_planner.features.signup.data.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
