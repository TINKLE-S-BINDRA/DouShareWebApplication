package com.example.lendingplatform.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Borrow borrow;

    private Double amount;

    private String status; // Pending, Paid, Failed

    private String paymentProvider;

    private LocalDateTime paymentDate;
}