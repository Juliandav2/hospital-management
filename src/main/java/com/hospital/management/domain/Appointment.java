package com.hospital.management.domain;


import com.hospital.management.domain.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "appointments")
@Entity
public class Appointment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn (name = "patient_id")
    @ManyToOne (fetch = FetchType.LAZY)
    private Patient patient;

    @JoinColumn (name = "doctor_id")
    @ManyToOne (fetch = FetchType.LAZY)
    private Doctor doctor;

    @Column (name = "scheduled_at")
    private Instant scheduledAt;

    @Enumerated (EnumType.STRING)
    private AppointmentStatus status;

    private String notes;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column (nullable = false)
    private boolean deleted;
}
