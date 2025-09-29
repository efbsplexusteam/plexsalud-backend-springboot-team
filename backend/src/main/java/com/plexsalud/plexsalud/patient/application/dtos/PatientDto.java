package com.plexsalud.plexsalud.patient.application.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PatientDto {
    @Schema(description = "Full name of the patient", example = "John Doe")
    private String fullName;

    @Schema(description = "Email of the patient", example = "johndoe@example.com")
    private String email;

    @Schema(description = "Password for the patient's account", example = "SecurePassword123")
    private String password;

    @Schema(description = "Age of the patient", example = "30")
    private Integer age;

    @Schema(description = "Gender of the patient", example = "Male")
    private String gender;

    public void setFullName(String fullName) {
        this.fullName = (fullName != null) ? fullName.toLowerCase() : null;
    }

    public void setGender(String gender) {
        this.gender = (gender != null) ? gender.toLowerCase() : null;
    }

    public void setEmail(String email) {
        this.email = (email != null) ? email.toLowerCase() : null;
    }
}
