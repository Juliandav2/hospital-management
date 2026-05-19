package com.hospital.management.mapper;

import com.hospital.management.domain.Patient;
import com.hospital.management.domain.enums.Gender;
import com.hospital.management.dto.request.PatientCreateRequest;
import com.hospital.management.dto.response.PatientResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class PatientMapper {

    public PatientResponse toResponse (Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender() != null ? patient.getGender().name() : null)
                .address(patient.getAddress())
                .build();
    }

    public Patient toEntity (PatientCreateRequest request) {
        Patient patient = new Patient();
        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setEmail(request.getEmail());
        patient.setPhone(request.getPhone());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender() != null ? Gender.valueOf(request.getGender()) : null);
        patient.setAddress(request.getAddress());
        patient.setDeleted(false);
        patient.setCreatedAt(Instant.now());
        patient.setUpdatedAt(Instant.now());
        return patient;
    }
}
