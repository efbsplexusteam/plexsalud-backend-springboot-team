package com.plexsalud.plexsalud.doctor.infrastructure.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.plexsalud.plexsalud.auth.dtos.LoginDto;
import com.plexsalud.plexsalud.auth.responses.LoginResponse;
import com.plexsalud.plexsalud.auth.services.AuthenticationService;
import com.plexsalud.plexsalud.auth.services.JwtService;
import com.plexsalud.plexsalud.doctor.application.dtos.DoctorDto;
import com.plexsalud.plexsalud.doctor.application.dtos.DoctorFullNameAndIdDto;
import com.plexsalud.plexsalud.doctor.application.reponses.DoctorResponse;
import com.plexsalud.plexsalud.doctor.application.services.DoctorService;
import com.plexsalud.plexsalud.doctor.domain.entities.Doctor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Doctors", description = "Operations with doctors")
@RequestMapping("/api/v2/doctor")
@RestController
public class DoctorController {
    private final DoctorService doctorService;
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public DoctorController(DoctorService doctorService, JwtService jwtService,
            AuthenticationService authenticationService) {
        this.doctorService = doctorService;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Register a new doctor", description = "This endpoint allows a new doctor to register by providing full name, specialty, email, and password.")
    @PostMapping("signup")
    public DoctorResponse savePatient(HttpServletRequest request,
            @RequestBody DoctorDto doctorDto) {

        DoctorResponse saved = doctorService.save(doctorDto);
        return saved;
    }

    @Operation(summary = "Doctor login", description = "This endpoint allows a registered doctor to log in by providing their email and password.")
    @PostMapping("login")
    public LoginResponse authenticate(@RequestBody LoginDto loginDto) {
        Doctor authenticatedUser = authenticationService.authenticateDoctor(loginDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setAccessToken(jwtToken)
                .setFullName(authenticatedUser.getFullName()).setId(authenticatedUser.getId())
                .setExpiresIn(jwtService.getExpirationTime());

        return loginResponse;
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("specialties")
    @Operation(summary = "Get all specialties", description = "This endpoint retrieves a list of all specialties available for doctors.")
    public List<String> findAllSpecialties() {
        List<String> specialties = doctorService.findAllSpecialties();

        return specialties;
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("doctors-by-specialty")
    @Operation(summary = "Get doctors by specialty", description = "This endpoint retrieves a list of doctors based on the specialty provided.")
    public List<DoctorFullNameAndIdDto> findAllDoctorsBySpecialty(String specialty) {
        List<DoctorFullNameAndIdDto> doctors = doctorService.findAllDoctorsBySpecialty(specialty);

        return doctors;
    }

}
