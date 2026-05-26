package com.hospital.management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AppointmentCreateRequest {

    @NotNull (message = "Patient ID cannot be null")
    private Long patientId;

    @NotNull (message = "Doctor ID cannot be null")
    private Long doctorId;

    @NotBlank (message = "Scheduled date cannot be blank")
    private String scheduledAt;

    private String notes;
}
