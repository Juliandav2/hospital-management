package com.hospital.management.controller;

import com.hospital.management.dto.request.AppointmentCreateRequest;
import com.hospital.management.dto.response.AppointmentResponse;
import com.hospital.management.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments () {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById (@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment (@RequestBody @Valid AppointmentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.createAppointment(request));
    }
}
