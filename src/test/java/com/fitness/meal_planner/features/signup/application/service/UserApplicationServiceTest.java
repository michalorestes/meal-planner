package com.fitness.meal_planner.features.signup.application.service;

import com.fitness.meal_planner.features.signup.application.dto.CreateUserCommand;
import com.fitness.meal_planner.features.signup.application.dto.UserDto;
import com.fitness.meal_planner.features.signup.application.exception.UserExistsException;
import com.fitness.meal_planner.features.signup.domain.entity.User;
import com.fitness.meal_planner.features.signup.domain.repository.UserRepository;
import com.fitness.meal_planner.features.signup.domain.valueobject.EmailAddress;
import com.fitness.meal_planner.features.signup.domain.valueobject.Password;
import com.fitness.meal_planner.features.signup.domain.valueobject.UserId;
import com.fitness.meal_planner.features.signup.domain.valueobject.Username;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserApplicationServiceTest {

    @Test
    public void testCreateUser() throws UserExistsException {
        UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);
        PasswordEncoderServiceInterface passwordEncoderServiceMock = Mockito.mock(PasswordEncoderServiceInterface.class);

        User userCreated = new User(
            new UserId(1L), 
            new Username("testUser"), 
            new EmailAddress("test@mail.com"), 
            new Password("testPassword"), 
            LocalDateTime.now()
        );
        
        when(userRepositoryMock.findByEmail("test@mail.com")).thenReturn(Optional.empty());
        when(userRepositoryMock.save(any(User.class))).thenReturn(userCreated);

        when(passwordEncoderServiceMock.encode("testPassword")).thenReturn("encodedPassword");

        CreateUserCommand createUserCommand = new CreateUserCommand(
            "test@mail.com", 
            "testUser", 
            "testPassword"
        );

        UserApplicationService userApplicationService = new UserApplicationService(userRepositoryMock, passwordEncoderServiceMock);
        
        UserDto userDto = userApplicationService.createUser(createUserCommand);

        assertEquals(1, userDto.id());
    }

    @Test
    public void testHandlesExistingUser() throws UserExistsException {
        UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);
        PasswordEncoderServiceInterface passwordEncoderServiceMock = Mockito.mock(PasswordEncoderServiceInterface.class);

        User user = new User(
            new UserId(1L), 
            new Username("testUser"), 
            new EmailAddress("test@mail.com"), 
            new Password("testPassword"), 
            LocalDateTime.now()
        );
        
        when(userRepositoryMock.findByEmail("test@mail.com")).thenReturn(Optional.of(user));

        CreateUserCommand createUserCommand = new CreateUserCommand(
            "test@mail.com", 
            "testUser", 
            "testPassword"
        );

        UserApplicationService userApplicationService = new UserApplicationService(userRepositoryMock, passwordEncoderServiceMock);
        UserExistsException exception = assertThrows(
            UserExistsException.class, 
            () -> userApplicationService.createUser(createUserCommand)
        );
        
        String expectedMessage = "User with email test@mail.com already exists";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
