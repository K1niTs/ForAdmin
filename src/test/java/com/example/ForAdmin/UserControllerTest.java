package com.example.ForAdmin;


import org.example.controller.UserController;
import org.example.model.User;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser() {
        User user = new User(1, "Alice");

        when(userService.createUser(anyString())).thenReturn(user);

        User createdUser = userController.createUser(user);

        assertNotNull(createdUser);
        assertEquals("Alice", createdUser.getName());

        verify(userService, times(1)).createUser("Alice");
    }

    @Test
    void getAllUsers() {
        List<User> users = Arrays.asList(new User(1, "Alice"), new User(2, "Bob"));

        when(userService.getAllUsers()).thenReturn(users);

        List<User> result = userController.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
    }

    @Test
    void getUserById() {
        User user = new User(1, "Alice");

        when(userService.getUserById(1)).thenReturn(Optional.of(user));

        User result = userController.getUserById(1);

        assertNotNull(result);
        assertEquals("Alice", result.getName());
    }

    @Test
    void updateUser() {
        User user = new User(1, "Updated Name");

        when(userService.updateUser(eq(1), anyString())).thenReturn(Optional.of(user));

        User result = userController.updateUser(1, user);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());

        verify(userService, times(1)).updateUser(1, "Updated Name");
    }

    @Test
    void deleteUser() {
        when(userService.deleteUser(1)).thenReturn(true);

        String response = userController.deleteUser(1);

        assertEquals("User deleted", response);
        verify(userService, times(1)).deleteUser(1);
    }
}
