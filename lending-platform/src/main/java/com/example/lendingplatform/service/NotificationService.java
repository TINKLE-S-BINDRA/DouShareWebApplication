package com.example.lendingplatform.service;

import com.example.lendingplatform.model.Notification;
import com.example.lendingplatform.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<Notification> getByUser(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public Notification getById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    public Notification markRead(Long id) {
        return notificationRepository.findById(id).map(notif -> {
            notif.setRead(true);
            return notificationRepository.save(notif);
        }).orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }
}