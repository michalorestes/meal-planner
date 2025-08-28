package com.fitness.meal_planner.features.signup.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.fitness.meal_planner.features.signup.data.model.UserModel;

public interface UserJpaRepository extends CrudRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}
