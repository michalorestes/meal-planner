package com.fitness.meal_planner.features.authentication.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fitness.meal_planner.features.authentication.application.service.AuthUtils;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        if (AuthUtils.isAuthenticated()) {
            return "redirect:/ingredients/view";
        }

        return "authentication/loginForm";
    }
    
}
