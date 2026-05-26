package com.hospital.management.mapper;

import com.hospital.management.domain.Appointment;
import com.hospital.management.domain.Doctor;
import com.hospital.management.domain.Patient;
import com.hospital.management.domain.enums.AppointmentStatus;
import com.hospital.management.dto.request.AppointmentCreateRequest;
import com.hospital.management.dto.response.AppointmentResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class AppointmentMapper {

    public AppointmentResponse toResponse (Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatient().getId())
                .patientName(appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName())
                .doctorId(appointment.getDoctor().getId())
                .doctorName(appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName())
                .scheduledAt(appointment.getScheduledAt().toString())
                .status(appointment.getStatus().name())
                .notes(appointment.getNotes())
                .build();
    }

    public Appointment toEntity (AppointmentCreateRequest request, Patient patient, Doctor doctor) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setScheduledAt(Instant.parse(request.getScheduledAt()));
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setNotes(request.getNotes());
        appointment.setDeleted(false);
        appointment.setCreatedAt(Instant.now());
        appointment.setUpdatedAt(Instant.now());
        return appointment;
    }
}
