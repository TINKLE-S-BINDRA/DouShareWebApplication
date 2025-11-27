package com.example.lendingplatform.service;

import com.example.lendingplatform.model.Payment;
import com.example.lendingplatform.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment create(Payment payment) {
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus("Pending");
        return paymentRepository.save(payment);
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payment updateStatus(Long id, String status) {
        return paymentRepository.findById(id).map(pay -> {
            pay.setStatus(status);
            return paymentRepository.save(pay);
        }).orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}