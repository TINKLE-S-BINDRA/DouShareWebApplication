package com.example.lendingplatform.repository;

import com.example.lendingplatform.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}