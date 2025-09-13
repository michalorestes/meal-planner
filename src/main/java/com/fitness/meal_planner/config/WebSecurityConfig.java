package com.fitness.meal_planner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.fitness.meal_planner.features.authentication.application.service.UserApplicationService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserApplicationService userApplicationService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((auhorize) -> 
            auhorize
                    .requestMatchers("/signup", "/login", "/css/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/signup").permitAll()
                    .anyRequest().authenticated()
        )
        .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/ingredients/view").permitAll())
        .logout(logout -> logout.permitAll())
        .userDetailsService(userApplicationService);

        return http.build();
    }
}
