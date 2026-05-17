package com.hospital.management.service;

import com.hospital.management.dto.request.PatientCreateRequest;
import com.hospital.management.domain.enums.Gender;
import java.time.Instant;
import com.hospital.management.domain.Patient;
import com.hospital.management.dto.response.PatientResponse;
import com.hospital.management.exception.ResourceNotFoundException;
import com.hospital.management.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAllByDeletedFalse()
                .stream()
                .map(patient -> PatientResponse.builder()
                        .id(patient.getId())
                        .firstName(patient.getFirstName())
                        .lastName(patient.getLastName())
                        .email(patient.getEmail())
                        .phone(patient.getPhone())
                        .dateOfBirth(patient.getDateOfBirth())
                        .gender(patient.getGender() != null ? patient.getGender().name() : null)
                        .address(patient.getAddress())
                        .build())
                .collect(Collectors.toList());
    }

    public PatientResponse getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));

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

    public PatientResponse createPatient(PatientCreateRequest request) {
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

        Patient saved = patientRepository.save(patient);

        return PatientResponse.builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .email(saved.getEmail())
                .phone(saved.getPhone())
                .dateOfBirth(saved.getDateOfBirth())
                .gender(saved.getGender() != null ? saved.getGender().name() : null)
                .address(saved.getAddress())
                .build();
    }
}

