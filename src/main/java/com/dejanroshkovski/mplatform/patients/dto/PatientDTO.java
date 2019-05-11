package com.dejanroshkovski.mplatform.patients.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class PatientDTO{

    private Integer id;

    @NotEmpty(message="firstName cannot be empty")
    private String firstName;

    @NotEmpty(message="lastName cannot be empty")
    private String lastName;

    // Must be 8 digit code
    @Pattern(regexp="\\d{8}", message="Must be 8 digit code")
    private String healthInsuranceId;
}