package com.plexsalud.plexsalud.doctor.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DoctorDto {

    @Schema(description = "Full name of the doctor", example = "Dr. John Doe")
    private String fullName;

    @Schema(description = "Specialty of the doctor", example = "Cardiology")
    private String specialty;

    @Schema(description = "Email address of the doctor", example = "dr.johndoe@example.com")
    private String email;

    @Schema(description = "Password for the doctor's account", example = "DoctorSecurePass123")
    private String password;

    public void setFullName(String fullName) {
        this.fullName = (fullName != null) ? fullName.toLowerCase() : null;
    }

    public void setSpecialty(String specialty) {
        this.specialty = (specialty != null) ? specialty.toLowerCase() : null;
    }
}