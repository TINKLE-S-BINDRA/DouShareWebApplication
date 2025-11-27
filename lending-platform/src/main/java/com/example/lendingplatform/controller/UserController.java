package com.example.lendingplatform.controller;

import com.example.lendingplatform.model.User;
import com.example.lendingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.lendingplatform.service.UserService;
import com.example.lendingplatform.security.JwtUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // REGISTER USER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User savedUser = userService.register(user);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // LOGIN (returns JWT)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        try {
            User user = userService.login(loginUser.getEmail(), loginUser.getPassword());
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok("Bearer " + token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // GET ALL USERS
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }
}