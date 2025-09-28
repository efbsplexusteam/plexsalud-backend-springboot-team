package com.plexsalud.plexsalud.appointment.reponses;

import java.time.OffsetDateTime;

public record AppointmentResponse(Long id, OffsetDateTime date, String status) {
}
