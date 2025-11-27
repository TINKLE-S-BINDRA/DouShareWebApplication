package com.example.lendingplatform.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Double pricePerDay;
    private Double securityDeposit;
    private String status; // Available, Requested, Borrowed
    private String pickupLocation;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}