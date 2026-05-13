package com.hospital.management.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "doctors")
@Entity
public class Doctor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "email", nullable = false, unique = true)
    private String email;

    @Column (name = "license_number", nullable = false, unique = true)
    private String licenseNumber;

    @Column (name = "phone")
    private String phone;

    @JoinColumn (name = "specialty_id")
    @ManyToOne (fetch = FetchType.LAZY)
    private Specialty specialty;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column (nullable = false)
    private boolean deleted;
}
