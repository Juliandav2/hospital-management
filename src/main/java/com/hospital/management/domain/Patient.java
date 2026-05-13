package com.hospital.management.domain;

import com.hospital.management.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Table (name = "patients")
@Entity
public class Patient {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "email", nullable = false, unique = true)
    private String email;

    @Column (name = "phone")
    private String phone;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column (name = "gender")
    @Enumerated (EnumType.STRING)
    private Gender gender;

    private String address;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column (nullable = false)
    private boolean deleted;
}
