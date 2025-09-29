package com.plexsalud.plexsalud.doctor.application.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.plexsalud.plexsalud.doctor.application.dtos.DoctorDto;
import com.plexsalud.plexsalud.doctor.application.dtos.DoctorFullNameAndIdAndSpecialtyDto;
import com.plexsalud.plexsalud.doctor.application.dtos.DoctorFullNameAndIdDto;
import com.plexsalud.plexsalud.doctor.application.reponses.DoctorResponse;
import com.plexsalud.plexsalud.doctor.domain.entities.Doctor;
import com.plexsalud.plexsalud.doctor.infrastructure.repositories.DoctorRepository;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public DoctorResponse save(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();

        doctor.setFullName(doctorDto.getFullName());
        doctor.setSpecialty(doctorDto.getSpecialty());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setPassword(passwordEncoder.encode(doctorDto.getPassword()));

        doctor = doctorRepository.save(doctor);
        DoctorResponse doctorResponse = new DoctorResponse(doctor.getFullName(),
                doctor.getSpecialty());

        return doctorResponse;
    }

    public List<String> findAllSpecialties() {
        List<String> specialties = doctorRepository.findDistinctSpecialty();

        return specialties;
    }

    public List<DoctorFullNameAndIdDto> findAllDoctorsBySpecialty(String specialty) {
        List<DoctorFullNameAndIdDto> doctors = doctorRepository.findAllDoctorsBySpecialty(specialty);

        return doctors;
    }

    public List<DoctorFullNameAndIdAndSpecialtyDto> findAllDoctors() {
        List<DoctorFullNameAndIdAndSpecialtyDto> doctors = doctorRepository.findAllDoctors();

        return doctors;
    }
}
