package com.plexsalud.plexsalud.appointment.infrastructure.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.plexsalud.plexsalud.appointment.application.reponses.DoctorAppointmentResponse;
import com.plexsalud.plexsalud.appointment.application.reponses.PatientAppointmentResponse;
import com.plexsalud.plexsalud.appointment.domain.entities.Appointment;
import com.plexsalud.plexsalud.doctor.domain.entities.Doctor;
import com.plexsalud.plexsalud.patient.domain.entities.Patient;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    @Query("SELECT a.id, a.date, a.status, p.fullName, p.age, p.gender, p.email FROM Appointment a JOIN a.patient p WHERE a.doctor = :doctor")
    List<DoctorAppointmentResponse> findAllAppointmentsByDoctor(Doctor doctor);

    @Query("SELECT a.id, a.date, a.status, d.fullName, d.email, d.specialty FROM Appointment a JOIN a.doctor d WHERE a.patient = :patient")
    List<PatientAppointmentResponse> findAllAppointmentsByPatient(Patient patient);
}
