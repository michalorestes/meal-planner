package com.fitness.meal_planner.features.signup.application.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordEncryptionService implements PasswordEncryptionServiceInterface {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String encrypt(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
