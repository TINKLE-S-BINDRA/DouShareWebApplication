package com.example.lendingplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    // Home page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Auth pages
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // Users page
    @GetMapping("/users")
    public String users() {
        return "users";
    }

    // Items pages
    @GetMapping("/items")
    public String items() {
        return "items";
    }

    @GetMapping("/items/create")
    public String createItem() {
        return "item-create";
    }

    @GetMapping("/items/details")
    public String itemDetails() {
        return "item-details";
    }

    // Borrow pages
    @GetMapping("/borrows")
    public String borrows() {
        return "borrows";
    }

    // Requests pages
    @GetMapping("/requests")
    public String requests() {
        return "requests";
    }

    // Conversations (messages)
    @GetMapping("/conversations")
    public String conversations() {
        return "conversations";
    }

    @GetMapping("/messages")
    public String messages() {
        return "messages";
    }

    // Notifications page
    @GetMapping("/notifications")
    public String notifications() {
        return "notifications";
    }

    // Payments page
    @GetMapping("/payments")
    public String payments() {
        return "payments";
    }

    // Profile pages
    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String profileEdit() {
        return "profile-edit";
    }
}
