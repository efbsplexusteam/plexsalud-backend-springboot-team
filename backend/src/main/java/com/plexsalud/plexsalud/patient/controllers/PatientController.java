package com.plexsalud.plexsalud.patient.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plexsalud.plexsalud.auth.dtos.LoginDto;
import com.plexsalud.plexsalud.auth.responses.LoginResponse;
import com.plexsalud.plexsalud.auth.services.AuthenticationService;
import com.plexsalud.plexsalud.auth.services.JwtService;
import com.plexsalud.plexsalud.patient.dtos.PatientDto;
import com.plexsalud.plexsalud.patient.entities.Patient;
import com.plexsalud.plexsalud.patient.responses.PatientResponse;
import com.plexsalud.plexsalud.patient.services.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Patients", description = "Operations with patients")
@RequestMapping("/api/v2/patient")
@RestController
public class PatientController {

    private final PatientService patientService;
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public PatientController(PatientService patientService, JwtService jwtService,
            AuthenticationService authenticationService) {
        this.patientService = patientService;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Sign up a new patient", description = "This endpoint allows a new patient to register by providing full name, email, password, etc.")
    @PostMapping("signup")
    public ResponseEntity<PatientResponse> savePatient(HttpServletRequest request,
            @RequestBody PatientDto patientDto) {

        PatientResponse saved = patientService.save(patientDto);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Patient login", description = "This endpoint allows a registered patient to log in by providing their email and password.")
    @PostMapping("login")
    public LoginResponse authenticate(@RequestBody LoginDto loginDto) {
        Patient authenticatedUser = authenticationService.authenticatePatient(loginDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setAccessToken(jwtToken)
                .setFullName(authenticatedUser.getFullName()).setId(authenticatedUser.getId())
                .setExpiresIn(jwtService.getExpirationTime());

        return loginResponse;
    }

}
