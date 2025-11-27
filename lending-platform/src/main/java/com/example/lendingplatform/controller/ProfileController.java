package com.example.lendingplatform.controller;

import com.example.lendingplatform.model.Profile;
import com.example.lendingplatform.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    // GET /profiles — list all profiles
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAll());
    }

    // GET /profiles/{userId} — get one user's profile
    @GetMapping("/{userId}")
    public ResponseEntity<Profile> getProfileByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(profileService.getByUserId(userId));
    }

    // POST /profiles — create a profile
    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        return ResponseEntity.ok(profileService.create(profile));
    }

    // PUT /profiles/{userId} — update profile
    @PutMapping("/{userId}")
    public ResponseEntity<Profile> updateProfile(
            @PathVariable Long userId,
            @RequestBody Profile updatedProfile) {

        return ResponseEntity.ok(profileService.update(userId, updatedProfile));
    }
}
