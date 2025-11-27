package com.example.lendingplatform.controller;

import com.example.lendingplatform.model.*;
import com.example.lendingplatform.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.lendingplatform.dto.AdminLoginRequest;
import com.example.lendingplatform.dto.AdminLoginResponse;
import com.example.lendingplatform.security.JwtUtil;

import java.util.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {


    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final RequestRepository requestRepository;
    private final ReviewRepository reviewRepository;
    private final BorrowRepository borrowRepository;
    private final PaymentRepository paymentRepository;


    @PostMapping("/login")
    public AdminLoginResponse login(@RequestBody AdminLoginRequest request) {

        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(admin.getUsername());

        return new AdminLoginResponse(token);
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }



    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @DeleteMapping("/items/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return "Item deleted successfully";
    }


    @GetMapping("/analytics")
    public Map<String, Object> getAnalytics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalItems", itemRepository.count());
        stats.put("totalRequests", requestRepository.count());
        stats.put("totalReviews", reviewRepository.count());
        stats.put("totalBorrows", borrowRepository.count());
        stats.put("totalPayments", paymentRepository.count());
        return stats;
    }
}