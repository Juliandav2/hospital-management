package com.hospital.management.service;

import com.hospital.management.domain.Doctor;
import com.hospital.management.domain.Specialty;
import com.hospital.management.dto.request.DoctorCreateRequest;
import com.hospital.management.dto.response.DoctorResponse;
import com.hospital.management.exception.ResourceNotFoundException;
import com.hospital.management.mapper.DoctorMapper;
import com.hospital.management.repository.DoctorRepository;
import com.hospital.management.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final SpecialtyRepository specialtyRepository;

    @Transactional (readOnly = true)
    public List<DoctorResponse> getAllDoctors () {
        return doctorRepository.findAllByDeletedFalse()
                .stream()
                .map(doctorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional (readOnly = true)
    public DoctorResponse getDoctorById (Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        return doctorMapper.toResponse(doctor);
    }

    public DoctorResponse createDoctor (DoctorCreateRequest request) {
        Specialty specialty = specialtyRepository.findById(request.getSpecialtyId())
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with id: " + request.getSpecialtyId()));

        Doctor saved = doctorRepository.save(doctorMapper.toEntity(request, specialty));
        return doctorMapper.toResponse(saved);
    }

}
