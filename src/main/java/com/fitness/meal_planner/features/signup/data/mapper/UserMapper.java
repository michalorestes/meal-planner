package com.fitness.meal_planner.features.signup.data.mapper;

import com.fitness.meal_planner.features.signup.data.model.UserModel;
import com.fitness.meal_planner.features.signup.domain.entity.User;

public final class UserMapper {

    private UserMapper() {

    }

    public static User toDomainEntity(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        return new User(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getEmail(),
                userModel.getPasswordHash(),
                userModel.getCreatedAt()
        );
    }

    public static UserModel toModel(User user) {
        if (user == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        userModel.setId(user.id());
        userModel.setUsername(user.username());
        userModel.setEmail(user.email());
        userModel.setPasswordHash(user.passwordHash());
        userModel.setCreatedAt(user.createdAt());

        return userModel;
    }
}
