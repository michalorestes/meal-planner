package com.fitness.meal_planner.features.signup.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpController {
    @GetMapping("/signup")
    public String signUp() {
        return "signup/signupForm";
    }
}
