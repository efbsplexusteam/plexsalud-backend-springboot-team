package com.plexsalud.plexsalud.patient.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.plexsalud.plexsalud.patient.entities.Patient;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);
}
