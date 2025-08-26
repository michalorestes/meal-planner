package com.fitness.meal_planner.features.signup.domain.repositories;

import com.fitness.meal_planner.features.signup.data.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCanSaveUser() {
        // given
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPasswordHash("password123");
        user.setCreatedAt(LocalDateTime.now());

        // when
        User savedUser = userRepository.save(user);

        // then
        User foundUser = entityManager.find(User.class, savedUser.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void testCanFindUserById() {
        // given
        User user = new User();
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
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
    }
}
