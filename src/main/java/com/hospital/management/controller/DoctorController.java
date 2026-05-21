package com.hospital.management.controller;

import com.hospital.management.dto.request.DoctorCreateRequest;
import com.hospital.management.dto.response.DoctorResponse;
import com.hospital.management.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getAllDoctors () {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById (@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor (@RequestBody @Valid DoctorCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(doctorService.createDoctor(request));
    }
}
