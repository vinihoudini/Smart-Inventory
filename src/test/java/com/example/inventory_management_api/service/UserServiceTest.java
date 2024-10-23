package com.example.inventory_management_api.service;

import com.example.inventory_management_api.exception.InvalidEmailException;
import com.example.inventory_management_api.exception.UserNotFoundException;
import com.example.inventory_management_api.model.User;
import com.example.inventory_management_api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");

        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.create(user);

        assertEquals("testUser", createdUser.getUsername());
        assertEquals("test@example.com", createdUser.getEmail());
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User foundUser = userService.findById(1L);

        assertEquals("testUser", foundUser.getUsername());
    }

    @Test
    public void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("oldUser");

        User updatedUser = new User();
        updatedUser.setUsername("newUser");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(existingUser)).thenReturn(updatedUser);

        User result = userService.update(1L, updatedUser);
        assertEquals("newUser", result.getUsername());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId(1L);

        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        userService.delete(2L);

        Mockito.verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUserNotFoundException() {
        Mockito.when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.findById(99L);
        });
    }

    @Test
    public void testUserInvalidEmail() {
        User user = new User();
        user.setEmail("invalidemail");

        assertThrows(InvalidEmailException.class, () -> {
            userService.create(user);
        });
    }

    @Test
    public void testCreateUserWithNullEmail() {
        User user = new User();
        user.setEmail(null);

        assertThrows(NullPointerException.class, () -> {
            userService.create(user);
        });
    }

    @Test
    public void testPasswordEncryption() {
        User user = new User();
        user.setPassword("plainPassword");

        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.create(user);
        assertNotEquals("plainPassword", createdUser.getPassword());
    }

    @Test
    public void testFindAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testUserRepositorySave() {
        User user = new User();
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.create(user);
        assertEquals(user, savedUser);
    }

}

