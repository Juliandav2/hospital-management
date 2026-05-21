package com.hospital.management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DoctorResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String licenseNumber;

    private String phone;

    private Long specialtyId;

    private String specialtyName;
}