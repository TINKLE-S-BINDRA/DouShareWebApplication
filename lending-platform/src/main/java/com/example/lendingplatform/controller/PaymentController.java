package com.example.lendingplatform.controller;

import com.example.lendingplatform.model.Payment;
import com.example.lendingplatform.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    // Create payment
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus("Pending");
        return paymentRepository.save(payment);
    }

    // Get all payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Get payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long id) {
        return paymentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update payment status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Payment> updatePaymentStatus(@PathVariable Long id, @RequestBody String status) {
        return paymentRepository.findById(id).map(payment -> {
            payment.setStatus(status);
            return ResponseEntity.ok(paymentRepository.save(payment));
        }).orElse(ResponseEntity.notFound().build());
    }
}