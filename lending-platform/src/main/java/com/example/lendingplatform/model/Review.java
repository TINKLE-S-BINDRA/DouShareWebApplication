package com.example.lendingplatform.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User reviewer;

    @ManyToOne
    private User reviewee;

    @ManyToOne
    private Borrow borrow;

    private int rating;

    private String reviewText;

    private LocalDateTime reviewDate;
}