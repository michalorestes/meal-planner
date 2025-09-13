package com.fitness.meal_planner.features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ScratchTest {
    @Test
    public void testPassword() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = encoder.encode("password");
        boolean isValidPassword = encoder.matches("password", encodedPassword);

        assertEquals(true, isValidPassword);
    }
}
