package com.fitness.meal_planner.features.signup.application.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fitness.meal_planner.features.authentication.application.service.PasswordEncoderService;
import com.fitness.meal_planner.features.authentication.application.service.PasswordEncoderServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderServiceTest {
    @Test
    public void testCanEncryptPassword() {

        PasswordEncoderServiceInterface encryptionService = new PasswordEncoderService(new BCryptPasswordEncoder());
        String rawPassword = "testPassword";
        String encryptedPassword = encryptionService.encode(rawPassword);

        assertNotNull(encryptedPassword);
        assertNotEquals(rawPassword, encryptedPassword);
    }
}
