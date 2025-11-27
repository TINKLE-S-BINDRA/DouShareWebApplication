package com.example.lendingplatform.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role; // Student, Lender, Admin

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;
}