package com.fitness.meal_planner.features.signup.data.repository;

import com.fitness.meal_planner.features.signup.data.model.UserModel;

import com.fitness.meal_planner.features.signup.domain.entity.User;
import com.fitness.meal_planner.features.signup.domain.valueobject.EmailAddress;
import com.fitness.meal_planner.features.signup.domain.valueobject.PasswordHashed;
import com.fitness.meal_planner.features.signup.domain.valueobject.Username;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(UserRepositoryImpl.class)
public class UserRepositoryImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testCanSaveUser() {
        // given
        User user = new User(
                null,
                new Username("testUser"),
                new EmailAddress("testuser@example.com"),
                new PasswordHashed("password123"),
                LocalDateTime.now()
        );

        // when
        User savedUser = userRepository.save(user);

        // then
        UserModel foundUser = entityManager.find(UserModel.class, savedUser.id().userId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo(user.username().username());
    }

    @Test
    public void testCanFindUserByEmail() {
        // given
        UserModel user = new UserModel();
        user.setUsername("testUser");
        user.setEmail("testuser@example.com");
        user.setPasswordHash("password123");
        user.setCreatedAt(LocalDateTime.now());

        entityManager.persist(user);
        entityManager.flush();

        // when
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());

        // then
        assertTrue(foundUser.isPresent());
        assertThat(foundUser.get().username().username()).isEqualTo(user.getUsername());
    }

    @Test
    public void testReturnsEmptyOptionalWhenUserNotFound() {
        // when
        Optional<User> foundUser = userRepository.findByEmail("notexisting@mail.com");

        // then
        assertFalse(foundUser.isPresent());
    }
}
