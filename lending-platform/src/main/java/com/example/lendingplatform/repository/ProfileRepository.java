package com.example.lendingplatform.repository;

import com.example.lendingplatform.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}