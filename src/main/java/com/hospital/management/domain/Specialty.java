package com.hospital.management.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "specialties")
@NoArgsConstructor
@Getter
@Setter
public class Specialty {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "description")
    private String description;

    @Column (updatable = false, name = "created_at")
    private Instant createdAt;

    @Column (name = "updated_at")
    private Instant updatedAt;
}
