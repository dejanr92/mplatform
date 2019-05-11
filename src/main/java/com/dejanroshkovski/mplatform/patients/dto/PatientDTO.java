package com.dejanroshkovski.mplatform.patients.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.dejanroshkovski.mplatform.util.validations.ValidDateValidation;

import lombok.Data;

@Data
public class PatientDTO{

    private Integer id;

    @NotEmpty(message="firstName cannot be empty")
    private String firstName;

    @NotEmpty(message="lastName cannot be empty")
    private String lastName;

    // Must be 8 digit code
    // not required but must be valid
    @Pattern(regexp="\\d{8}", message="Must be 8 digit code")
    private String healthInsuranceId;

    @NotNull(message="Must provide examination Date")
    @ValidDateValidation(value="yyyy-MM-dd", message="Date of birth must be formatted yyyy-MM-dd")
    // Date has to be in yyyy-MM-dd format
    private String dateOfBirth;
}