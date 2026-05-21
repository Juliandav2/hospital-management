package com.hospital.management.service;

import com.hospital.management.domain.Patient;
import com.hospital.management.dto.request.PatientCreateRequest;
import com.hospital.management.dto.response.PatientResponse;
import com.hospital.management.exception.ResourceNotFoundException;
import com.hospital.management.mapper.PatientMapper;
import com.hospital.management.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Transactional (readOnly = true)
    public List<PatientResponse> getAllPatients () {
        return patientRepository.findAllByDeletedFalse()
                .stream()
                .map(patientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional (readOnly = true)
    public PatientResponse getPatientById (Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        return patientMapper.toResponse(patient);
    }

    public PatientResponse createPatient (PatientCreateRequest request) {
        Patient saved = patientRepository.save(patientMapper.toEntity(request));
        return patientMapper.toResponse(saved);
    }

}

