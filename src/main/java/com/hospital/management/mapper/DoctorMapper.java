package com.hospital.management.mapper;

import com.hospital.management.domain.Doctor;
import com.hospital.management.domain.Specialty;
import com.hospital.management.dto.request.DoctorCreateRequest;
import com.hospital.management.dto.response.DoctorResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DoctorMapper {

    public DoctorResponse toResponse (Doctor doctor) {
        return DoctorResponse.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .email(doctor.getEmail())
                .licenseNumber(doctor.getLicenseNumber())
                .phone(doctor.getPhone())
                .specialtyId(doctor.getSpecialty() != null ? doctor.getSpecialty().getId() : null)
                .specialtyName(doctor.getSpecialty() != null ? doctor.getSpecialty().getName() : null)
                .build();
                
    }

    public Doctor toEntity (DoctorCreateRequest request, Specialty specialty) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(request.getFirstName());
        doctor.setLastName(request.getLastName());
        doctor.setEmail(request.getEmail());
        doctor.setLicenseNumber(request.getLicenseNumber());
        doctor.setPhone(request.getPhone());
        doctor.setSpecialty(specialty);
        doctor.setDeleted(false);
        doctor.setCreatedAt(Instant.now());
        doctor.setUpdatedAt(Instant.now());
        return doctor;
    }
}
