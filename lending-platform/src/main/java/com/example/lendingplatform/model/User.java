package com.example.lendingplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String password;
    private String role; // Student, Lender, Admin

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;
}