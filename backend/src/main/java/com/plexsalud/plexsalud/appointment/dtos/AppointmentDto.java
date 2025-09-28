package com.plexsalud.plexsalud.appointment.dtos;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AppointmentDto {
    @Schema(description = "Appointment date", example = "2025-09-28T17:45:00+02:00")
    private OffsetDateTime date;

    @Schema(description = "Doctor ID", example = "1")
    private Long doctorId;

    @Schema(description = "Don't send", example = "null")
    private Long patientId;
}
