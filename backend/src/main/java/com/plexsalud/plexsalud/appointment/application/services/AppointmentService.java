package com.plexsalud.plexsalud.appointment.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plexsalud.plexsalud.appointment.application.dtos.AppointmentDto;
import com.plexsalud.plexsalud.appointment.application.reponses.AppointmentResponse;
import com.plexsalud.plexsalud.appointment.application.reponses.DoctorAppointmentResponse;
import com.plexsalud.plexsalud.appointment.application.reponses.PatientAppointmentResponse;
import com.plexsalud.plexsalud.appointment.domain.entities.Appointment;
import com.plexsalud.plexsalud.appointment.infrastructure.repositories.AppointmentRepository;
import com.plexsalud.plexsalud.doctor.domain.entities.Doctor;
import com.plexsalud.plexsalud.patient.domain.entities.Patient;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public AppointmentResponse createAppointment(AppointmentDto appointmentDto) {
        try {

            Doctor doctor = new Doctor();
            doctor.setId(appointmentDto.getDoctorId());
            Patient patient = new Patient();
            patient.setId(appointmentDto.getPatientId());

            Appointment appointment = new Appointment();

            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
            appointment.setDate(appointmentDto.getDate());
            appointment.setStatus("CREATED");

            Appointment appointmentSaved = appointmentRepository.save(appointment);

            return new AppointmentResponse(appointmentSaved.getId(), appointmentSaved.getDate(),
                    appointmentSaved.getStatus(), "");
        } catch (Exception e) {
            throw new RuntimeException("Error creating appointment", e);
        }
    }

    public List<PatientAppointmentResponse> getAllAppointmentsByPatient(Long patientId) {
        Patient patient = new Patient();
        patient.setId(patientId);
        return appointmentRepository.findAllAppointmentsByPatient(patient);
    }

    public List<DoctorAppointmentResponse> getAllAppointmentsByDoctor(Long doctorId) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        return appointmentRepository.findAllAppointmentsByDoctor(doctor);
    }

    @Transactional
    public AppointmentResponse deleteAppointment(Long id) {
        try {
            Optional<Appointment> appointment = appointmentRepository.findById(id);

            if (appointment.isPresent()) {
                appointment.get().setStatus("CANCEL");

                appointmentRepository.save(appointment.get());
            }

            return new AppointmentResponse(appointment.get().getId(), appointment.get().getDate(),
                    appointment.get().getStatus(), "");
        } catch (Exception e) {
            throw (e);
        }
    }

    @Transactional
    public AppointmentResponse updateAppointment(Long id) {
        try {
            Optional<Appointment> appointment = appointmentRepository.findById(id);

            if (appointment.isPresent()) {
                appointment.get().setStatus("CONFIRM");

                appointmentRepository.save(appointment.get());
            }

            return new AppointmentResponse(appointment.get().getId(), appointment.get().getDate(),
                    appointment.get().getStatus(), "");
        } catch (Exception e) {
            throw e;
        }
    }

}
