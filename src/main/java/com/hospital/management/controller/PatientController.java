package com.hospital.management.controller;

import com.hospital.management.dto.request.PatientCreateRequest;
import com.hospital.management.dto.response.PatientResponse;
import com.hospital.management.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/patients")
@RequiredArgsConstructor
@Service
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients () {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<PatientResponse> getPatientById (@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient (@RequestBody @Valid PatientCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(patientService.createPatient(request));
    }
}
