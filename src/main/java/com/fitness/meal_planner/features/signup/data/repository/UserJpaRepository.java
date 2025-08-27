package com.fitness.meal_planner.features.signup.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.fitness.meal_planner.features.signup.data.model.UserModel;

public interface UserJpaRepository extends CrudRepository<UserModel, Long> {
    UserModel findByEmail(String email);
}
