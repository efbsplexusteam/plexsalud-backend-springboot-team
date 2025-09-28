package com.plexsalud.plexsalud.doctor.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.plexsalud.plexsalud.doctor.dtos.DoctorFullNameAndIdDto;
import com.plexsalud.plexsalud.doctor.entities.Doctor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);

    @Query("SELECT DISTINCT d.specialty FROM Doctor d")
    List<String> findDistinctSpecialty();

    @Query("SELECT d.fullName, d.id FROM Doctor d WHERE d.specialty = :specialty")
    List<DoctorFullNameAndIdDto> findAllDoctorsBySpecialty(String specialty);
}
