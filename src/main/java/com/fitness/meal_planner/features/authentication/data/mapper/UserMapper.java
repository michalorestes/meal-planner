package com.fitness.meal_planner.features.authentication.data.mapper;

import com.fitness.meal_planner.features.authentication.data.model.UserModel;
import com.fitness.meal_planner.features.authentication.domain.entity.User;
import com.fitness.meal_planner.features.authentication.domain.valueobject.EmailAddress;
import com.fitness.meal_planner.features.authentication.domain.valueobject.PasswordHashed;
import com.fitness.meal_planner.features.authentication.domain.valueobject.UserId;
import com.fitness.meal_planner.features.authentication.domain.valueobject.Username;

public final class UserMapper {

    private UserMapper() {

    }

    public static User toDomainEntity(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        return new User(
                new UserId(userModel.getId()),
                new Username(userModel.getUsername()),
                new EmailAddress(userModel.getEmail()),
                new PasswordHashed(userModel.getPasswordHash()),
                userModel.getCreatedAt()
        );
    }

    public static UserModel toModel(User user) {
        if (user == null) {
            return null;
        }

        UserModel userModel = new UserModel();

        if (user.id() != null) {
            userModel.setId(user.id().userId());
        }

        userModel.setUsername(user.username().username());
        userModel.setEmail(user.email().email());
        userModel.setPasswordHash(user.passwordHashed().password());
        userModel.setCreatedAt(user.createdAt());

        return userModel;
    }
}
