package com.plexsalud.plexsalud.patient.infrastructure.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.plexsalud.plexsalud.patient.domain.entities.Patient;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);
}
