package com.fitness.meal_planner.features.signup.application.service;

import com.fitness.meal_planner.features.signup.application.dto.CreateUserCommand;
import com.fitness.meal_planner.features.signup.application.dto.UserDto;
import com.fitness.meal_planner.features.signup.application.exception.UserExistsException;
import com.fitness.meal_planner.features.signup.domain.entity.User;
import com.fitness.meal_planner.features.signup.domain.repository.UserRepository;
import com.fitness.meal_planner.features.signup.domain.valueobject.EmailAddress;
import com.fitness.meal_planner.features.signup.domain.valueobject.Password;
import com.fitness.meal_planner.features.signup.domain.valueobject.Username;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserApplicationService {

    private final UserRepository userRepository;
    private final PasswordEncoderServiceInterface passwordEncoderService;

    public UserDto createUser(CreateUserCommand createUserCommand) throws UserExistsException {
        User user = new User(
            null, 
            new Username(createUserCommand.username()),
            new EmailAddress(createUserCommand.email()),
            new Password(passwordEncoderService.encode(createUserCommand.password())),
            LocalDateTime.now()
        );

        Optional<User> userOptional = userRepository.findByEmail(user.email().email());

        if (userOptional.isPresent()) {
            throw new UserExistsException(user.email().email());
        }

        User createdUser = userRepository.save(user);

        return new UserDto(createdUser.id().userId());
    }
}
