package com.plexsalud.plexsalud.appointment.application.reponses;

import java.time.OffsetDateTime;

public record AppointmentResponse(Long id, OffsetDateTime date, String status, String name) {
}
