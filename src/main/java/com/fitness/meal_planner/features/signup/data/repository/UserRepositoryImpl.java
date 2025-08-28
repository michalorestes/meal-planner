package com.fitness.meal_planner.features.signup.data.repository;

import com.fitness.meal_planner.features.signup.data.mapper.UserMapper;
import com.fitness.meal_planner.features.signup.data.model.UserModel;
import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fitness.meal_planner.features.signup.domain.entity.User;
import com.fitness.meal_planner.features.signup.domain.repository.UserRepository;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserModel> model = userJpaRepository.findByEmail(email);

        return model.map(UserMapper::toDomainEntity);
    }

    @Override
    public User save(User user) {
        UserModel model = userJpaRepository.save(UserMapper.toModel(user));

        return UserMapper.toDomainEntity(model);
    }
    
}
