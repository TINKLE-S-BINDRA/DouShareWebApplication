package com.example.lendingplatform.controller;

import com.example.lendingplatform.model.Request;
import com.example.lendingplatform.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    // Create request
    @PostMapping
    public Request createRequest(@RequestBody Request request) {
        request.setStatus("Pending");
        return requestRepository.save(request);
    }

    // Get all requests
    @GetMapping
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    // Get requests for a user
    @GetMapping("/user/{userId}")
    public List<Request> getRequestsByUser(@PathVariable Long userId) {
        return requestRepository.findByBorrowerId(userId);
    }

    // Update request status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Request> updateStatus(@PathVariable Long id, @RequestBody String status) {
        return requestRepository.findById(id).map(request -> {
            request.setStatus(status);
            return ResponseEntity.ok(requestRepository.save(request));
        }).orElse(ResponseEntity.notFound().build());
    }
}