package com.hospital.management.service;

import com.hospital.management.domain.Appointment;
import com.hospital.management.domain.Doctor;
import com.hospital.management.domain.Patient;
import com.hospital.management.dto.request.AppointmentCreateRequest;
import com.hospital.management.dto.response.AppointmentResponse;
import com.hospital.management.exception.ResourceNotFoundException;
import com.hospital.management.mapper.AppointmentMapper;
import com.hospital.management.repository.AppointmentRepository;
import com.hospital.management.repository.DoctorRepository;
import com.hospital.management.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional (readOnly = true)
    public List<AppointmentResponse> getAllAppointments () {
        return appointmentRepository.findByDeletedFalse()
                .stream()
                .map(appointmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional (readOnly = true)
    public AppointmentResponse getAppointmentById (Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        return appointmentMapper.toResponse(appointment);
    }

    @Transactional
    public AppointmentResponse createAppointment (AppointmentCreateRequest request) {

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + request.getPatientId()));

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + request.getDoctorId()));

        Appointment saved = appointmentRepository.save(appointmentMapper.toEntity(request, patient, doctor));
        return appointmentMapper.toResponse(saved);
    }

}
