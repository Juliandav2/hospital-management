package com.hospital.management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DoctorCreateRequest {

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank (message = "Last name cannot be blank")
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank(message = "License number cannot be blank")
    private String licenseNumber;

    private String phone;

    private Long specialtyId;
}
