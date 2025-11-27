package com.example.lendingplatform.controller;

import com.example.lendingplatform.model.User;
import com.example.lendingplatform.security.JwtUtil;
import com.example.lendingplatform.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService, jwtUtil);
    }

    @Test
    void testRegisterUser() {
        User newUser = new User();
        newUser.setEmail("user1@example.com");
        newUser.setPassword("password123");

        when(userService.register(any(User.class))).thenReturn(newUser);

        var response = userController.register(newUser);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("user1@example.com", ((User) response.getBody()).getEmail());
    }


    @Test
    void testRegisterUser_EmailExists() {
        when(userService.register(any(User.class)))
                .thenThrow(new IllegalArgumentException("Email already exists"));

        User existing = new User();
        existing.setEmail("user1@example.com");

        var response = userController.register(existing);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Email already exists", response.getBody());
    }

    @Test
    void testLoginUser_Success() {
        User user = new User();
        user.setEmail("user1@example.com");
        user.setPassword("password123");

        when(userService.login("user1@example.com", "password123"))
                .thenReturn(user);

        when(jwtUtil.generateToken("user1@example.com"))
                .thenReturn("dummy-token");

        var response = userController.login(user);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Bearer dummy-token", response.getBody());
    }

    @Test
    void testLoginUser_InvalidPassword() {
        when(userService.login("user1@example.com", "wrongpassword"))
                .thenThrow(new IllegalArgumentException("Invalid credentials"));

        User loginReq = new User();
        loginReq.setEmail("user1@example.com");
        loginReq.setPassword("wrongpassword");

        var response = userController.login(loginReq);

        assertEquals(401, response.getStatusCode().value());
        assertEquals("Invalid credentials", response.getBody());
    }


    @Test
    void testGetAllUsers() {
        User u1 = new User(); u1.setEmail("user1@example.com");
        User u2 = new User(); u2.setEmail("user2@example.com");

        when(userService.getAll()).thenReturn(List.of(u1, u2));

        var users = userController.getAllUsers();

        assertEquals(2, users.size());
    }
}