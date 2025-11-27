package com.example.lendingplatform.dto;

import lombok.Data;

@Data
public class AdminLoginRequest {
    private String username;
    private String password;
}