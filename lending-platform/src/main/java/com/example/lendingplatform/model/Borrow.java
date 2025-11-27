package com.example.lendingplatform.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Request request;

    @ManyToOne
    private Item item;

    @ManyToOne
    private User borrower;

    @ManyToOne
    private User lender;

    private LocalDate borrowDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;

    private String status; // Active, Returned, Overdue

    private Double totalPayable;
}