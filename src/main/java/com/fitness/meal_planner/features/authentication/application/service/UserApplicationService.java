package com.fitness.meal_planner.features.authentication.application.service;

import com.fitness.meal_planner.features.authentication.application.dto.CreateUserCommand;
import com.fitness.meal_planner.features.authentication.application.dto.UserDto;
import com.fitness.meal_planner.features.authentication.application.exception.UserExistsException;
import com.fitness.meal_planner.features.authentication.domain.entity.User;
import com.fitness.meal_planner.features.authentication.domain.repository.UserRepository;
import com.fitness.meal_planner.features.authentication.domain.valueobject.EmailAddress;
import com.fitness.meal_planner.features.authentication.domain.valueobject.PasswordHashed;
import com.fitness.meal_planner.features.authentication.domain.valueobject.Username;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserApplicationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoderServiceInterface passwordEncoderService;

    public UserDto createUser(CreateUserCommand createUserCommand) throws UserExistsException {
        User user = new User(
            null, 
            new Username(createUserCommand.username()),
            new EmailAddress(createUserCommand.email()),
            new PasswordHashed(passwordEncoderService.encode(createUserCommand.password())),
            LocalDateTime.now()
        );

        Optional<User> userOptional = userRepository.findByEmail(user.email().email());

        if (userOptional.isPresent()) {
            throw new UserExistsException(user.email().email());
        }

        User createdUser = userRepository.save(user);

        return new UserDto(createdUser.id().userId());
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return new org.springframework.security.core.userdetails.User(
            user.email().email(),
            user.passwordHashed().password(),
            List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
