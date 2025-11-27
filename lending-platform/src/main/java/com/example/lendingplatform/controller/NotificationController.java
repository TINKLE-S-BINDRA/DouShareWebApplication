package com.example.lendingplatform.controller;

import com.example.lendingplatform.model.Notification;
import com.example.lendingplatform.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // List notifications for current user
    @GetMapping
    public List<Notification> getNotifications(@RequestParam Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Get a single notification
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable Long id) {
        return notificationRepository.findById(id)
                .map(notification -> ResponseEntity.ok(notification))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mark notification as read
    @PatchMapping("/{id}/read")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable Long id) {
        return notificationRepository.findById(id)
                .map(notification -> {
                    notification.setRead(true);
                    notificationRepository.save(notification);
                    return ResponseEntity.ok(notification);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a notification
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        return notificationRepository.findById(id)
                .map(notification -> {
                    notificationRepository.delete(notification);
                    // Explicitly parameterized to Void
                    return ResponseEntity.<Void>noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}