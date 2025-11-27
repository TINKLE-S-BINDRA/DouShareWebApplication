package com.example.lendingplatform.service;

import com.example.lendingplatform.model.Profile;
import com.example.lendingplatform.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    public Profile getByUserId(Long userId) {
        return profileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public Profile update(Long userId, Profile updatedProfile) {
        return profileRepository.findById(userId).map(profile -> {
            profile.setFullName(updatedProfile.getFullName());
            profile.setPhoneNumber(updatedProfile.getPhoneNumber());
            profile.setAddress(updatedProfile.getAddress());
            return profileRepository.save(profile);
        }).orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}