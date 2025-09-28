package com.plexsalud.plexsalud.appointment.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plexsalud.plexsalud.appointment.dtos.AppointmentDto;
import com.plexsalud.plexsalud.appointment.reponses.AppointmentResponse;
import com.plexsalud.plexsalud.appointment.services.AppointmentService;
import com.plexsalud.plexsalud.auth.services.JwtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Appointment", description = "Operations with appointments")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/v2/appointment")
@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final JwtService jwtService;

    public AppointmentController(AppointmentService appointmentService, JwtService jwtService) {
        this.appointmentService = appointmentService;
        this.jwtService = jwtService;
    }

    @Operation(summary = "Get all appointments for a patient", description = "This endpoint retrieves all appointments associated with a patient, based on their patient ID.")
    @GetMapping("patient")
    public List<AppointmentResponse> getAllAppointmentsByDoctorSearchByPatient(HttpServletRequest request) {
        Long id = jwtService.extractId(request);
        return appointmentService.getAllAppointmentsByPatient(id);
    }

    @Operation(summary = "Get all appointments for a doctor", description = "This endpoint retrieves all appointments associated with a doctor, based on their doctor ID.")
    @GetMapping("doctor")
    public List<AppointmentResponse> getAllAppointmentsByDoctor(HttpServletRequest request) {
        Long id = jwtService.extractId(request);
        return appointmentService.getAllAppointmentsByDoctor(id);
    }

    @Operation(summary = "Create a new appointment", description = "This endpoint creates a new appointment for a patient with a doctor, using the provided appointment details.")
    @PostMapping
    public AppointmentResponse createAppointment(@RequestBody AppointmentDto appointmentDto,
            HttpServletRequest request) {
        Long id = jwtService.extractId(request);
        appointmentDto.setPatientId(id);
        return appointmentService.createAppointment(appointmentDto);
    }

    @Operation(summary = "Confirm an existing appointment", description = "This endpoint updates an existing appointment, identified by its appointment ID.")
    @PatchMapping("/{id}")
    public AppointmentResponse updateAppointment(@PathVariable("id") Long id) {
        return appointmentService.updateAppointment(id);
    }

    @Operation(summary = "Cancel an appointment", description = "This endpoint deletes an existing appointment, identified by its appointment ID.")
    @DeleteMapping("/{id}")
    public AppointmentResponse deleteAppointment(@PathVariable("id") Long id) {
        return appointmentService.deleteAppointment(id);
    }
}
