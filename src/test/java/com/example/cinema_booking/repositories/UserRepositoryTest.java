package com.example.cinema_booking.repositories;

import com.example.cinema_booking.models.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.flush();

        user1 = new User();
        user1.setUsername("john_doe_" + UUID.randomUUID());
        user1.setPassword("password123");
        user1.setRoles("USER");

        user2 = new User();
        user2.setUsername("admin_" + UUID.randomUUID());
        user2.setPassword("adminpass");
        user2.setRoles("ADMIN");

        userRepository.saveAll(List.of(user1, user2));
    }

    @Test
    void shouldSaveAndFindUserById() {
        User found = userRepository.findById(user1.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo(user1.getUsername());
        assertThat(found.getRoles()).isEqualTo("USER");
    }

    @Test
    void shouldFindUserByUsername() {
        Optional<User> found = userRepository.findByUsername(user2.getUsername());
        assertThat(found).isPresent();
        assertThat(found.get().getPassword()).isEqualTo("adminpass");
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundById() {
        UUID randomId = UUID.randomUUID();
        assertThatThrownBy(() -> userRepository.findById(randomId)
                .orElseThrow(EntityNotFoundException::new))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void shouldFindAllUsers() {
        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(2)
                .extracting(User::getUsername)
                .containsExactlyInAnyOrder(user1.getUsername(), user2.getUsername());
    }

    @Test
    void shouldDeleteUser() {
        userRepository.delete(user1);
        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(1)
                .extracting(User::getUsername)
                .containsExactly(user2.getUsername());
    }
}
