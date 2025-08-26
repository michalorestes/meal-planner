package com.fitness.meal_planner.features.signup.application.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptionServiceTest {
    @Test
    public void testCanEncryptPassword() {

        PasswordEncryptionServiceInterface encryptionService = new PasswordEncryptionService(new BCryptPasswordEncoder());
        String rawPassword = "testPassword";
        String encryptedPassword = encryptionService.encrypt(rawPassword);

        assertNotNull(encryptedPassword);
        assertNotEquals(rawPassword, encryptedPassword);
    }
}
