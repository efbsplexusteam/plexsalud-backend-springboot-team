package com.plexsalud.plexsalud.patient.application.services;

import org.springframework.stereotype.Service;

import com.plexsalud.plexsalud.patient.application.dtos.PatientDto;
import com.plexsalud.plexsalud.patient.application.responses.PatientResponse;
import com.plexsalud.plexsalud.patient.domain.entities.Patient;
import com.plexsalud.plexsalud.patient.infrastructure.repositories.PatientRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PatientResponse save(PatientDto patientDto) {
        Patient patient = new Patient();

        patient.setFullName(patientDto.getFullName());
        patient.setAge(patientDto.getAge());
        patient.setGender(patientDto.getGender());
        patient.setEmail(patientDto.getEmail());
        patient.setPassword(passwordEncoder.encode(patientDto.getPassword()));

        patient = patientRepository.save(patient);
        PatientResponse PatientResponse = new PatientResponse(patient.getFullName());

        return PatientResponse;
    }

}
