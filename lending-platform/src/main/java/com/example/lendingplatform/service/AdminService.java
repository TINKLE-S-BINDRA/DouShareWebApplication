package com.example.lendingplatform.service;

import com.example.lendingplatform.model.Admin;
import com.example.lendingplatform.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    public Admin getById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    public void delete(Long id) {
        adminRepository.deleteById(id);
    }
}