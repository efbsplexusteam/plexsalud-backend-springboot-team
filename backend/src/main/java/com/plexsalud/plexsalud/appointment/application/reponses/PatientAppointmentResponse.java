package com.plexsalud.plexsalud.appointment.application.reponses;

import java.time.OffsetDateTime;

public record PatientAppointmentResponse(Long id, OffsetDateTime date, String status, String name, String email, String specialty) {
}
