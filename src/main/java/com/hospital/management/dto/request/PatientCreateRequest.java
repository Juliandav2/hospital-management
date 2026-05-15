package com.hospital.management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PatientCreateRequest {

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank (message = "Last name cannot be blank")
    private String lastName;

    @Email
    @NotBlank
    private String email;

    private String phone;

    private LocalDate dateOfBirth;

    private String gender;

    private String address;

}
