package com.plexsalud.plexsalud.appointment.application.reponses;

import java.time.OffsetDateTime;

public record DoctorAppointmentResponse(Long id, OffsetDateTime date, String status, String name, Integer age, String gender, String email) {
}
