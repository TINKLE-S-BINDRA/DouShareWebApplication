package com.example.lendingplatform.service;

import com.example.lendingplatform.model.Request;
import com.example.lendingplatform.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;

    public Request create(Request request) {
        request.setStatus("Pending");
        return requestRepository.save(request);
    }

    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    public List<Request> getByUser(Long userId) {
        return requestRepository.findByBorrowerId(userId);
    }

    public Request updateStatus(Long id, String status) {
        return requestRepository.findById(id).map(req -> {
            req.setStatus(status);
            return requestRepository.save(req);
        }).orElseThrow(() -> new RuntimeException("Request not found"));
    }
}