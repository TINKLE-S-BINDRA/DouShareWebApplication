package com.example.lendingplatform.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User borrower;

    @ManyToOne
    private Item item;

    private LocalDate startDate;
    private LocalDate endDate;

    private String status;
}
