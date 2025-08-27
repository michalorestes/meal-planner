package com.fitness.meal_planner.features.signup.data.repository;

import com.fitness.meal_planner.features.signup.data.model.UserModel;

import com.fitness.meal_planner.features.signup.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

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
                "testuser",
                "testuser@example.com",
                "password123",
                LocalDateTime.now()
        );

        // when
        User savedUser = userRepository.save(user);

        // then
        UserModel foundUser = entityManager.find(UserModel.class, savedUser.id());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo(user.username());
    }

    @Test
    public void testCanFindUserById() {
        // given
        UserModel user = new UserModel();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPasswordHash("password123");
        user.setCreatedAt(LocalDateTime.now());
        entityManager.persist(user);
        entityManager.flush();

        // when
        User foundUser = userRepository.findByEmail(user.getEmail());

        // then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.username()).isEqualTo(user.getUsername());
    }
}
