package com.fitness.meal_planner.features.authentication.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fitness.meal_planner.features.authentication.application.dto.CreateUserCommand;
import com.fitness.meal_planner.features.authentication.application.exception.UserExistsException;
import com.fitness.meal_planner.features.authentication.application.service.AuthUtils;
import com.fitness.meal_planner.features.authentication.application.service.UserApplicationService;
import com.fitness.meal_planner.features.authentication.web.dto.UserDetailsDto;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final UserApplicationService userApplicationService;

    @GetMapping("/signup")
    public String signUp() {
        if (AuthUtils.isAuthenticated()) {
            return "redirect:/ingredients/view";
        }

        return "authentication/signupForm";
    }

    @PostMapping("/signup")
    public String signUp(UserDetailsDto userDetailsDto) throws UserExistsException {
        if (AuthUtils.isAuthenticated()) {
            return "redirect:/ingredients/view";
        }

        userApplicationService.createUser(new CreateUserCommand(
            userDetailsDto.email(), 
            userDetailsDto.username(), 
            userDetailsDto.password()
        ));

        return "authentication/signUpCompleted";
    }
    
}
