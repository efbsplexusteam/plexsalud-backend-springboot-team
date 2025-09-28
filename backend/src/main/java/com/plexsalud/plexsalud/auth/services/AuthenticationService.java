package com.plexsalud.plexsalud.auth.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.plexsalud.plexsalud.auth.dtos.LoginDto;
import com.plexsalud.plexsalud.doctor.entities.Doctor;
import com.plexsalud.plexsalud.doctor.repositories.DoctorRepository;
import com.plexsalud.plexsalud.patient.entities.Patient;
import com.plexsalud.plexsalud.patient.repositories.PatientRepository;

@Service
public class AuthenticationService {
        private final PatientRepository patientRepository;

        private final DoctorRepository doctorRepository;

        private final AuthenticationManager patientAuthenticationManager;
        private final AuthenticationManager doctorAuthenticationManager;

        public AuthenticationService(
                        AuthenticationManager authenticationManager,
                        PatientRepository patientRepository,
                        DoctorRepository doctorRepository,
                        @Qualifier("patientAuthenticationManager") AuthenticationManager patientAuthenticationManager,
                        @Qualifier("doctorAuthenticationManager") AuthenticationManager doctorAuthenticationManager) {
                this.patientRepository = patientRepository;
                this.doctorRepository = doctorRepository;

                // custom
                this.patientAuthenticationManager = patientAuthenticationManager;
                this.doctorAuthenticationManager = doctorAuthenticationManager;
        }

        public Patient authenticatePatient(LoginDto input) {
                patientAuthenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
                return patientRepository.findByEmail(input.getEmail()).orElseThrow();
        }

        public Doctor authenticateDoctor(LoginDto input) {
                doctorAuthenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
                return doctorRepository.findByEmail(input.getEmail()).orElseThrow();
        }
}
