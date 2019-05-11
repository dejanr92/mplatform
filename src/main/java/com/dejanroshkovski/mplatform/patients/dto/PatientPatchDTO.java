package com.dejanroshkovski.mplatform.patients.dto;

import javax.validation.constraints.Pattern;

import com.dejanroshkovski.mplatform.util.validations.ValidDateValidation;

import lombok.Data;

@Data
public class PatientPatchDTO{

    private Integer id;

    private String firstName;

    private String lastName;

    // Must be 8 digit code
    @Pattern(regexp="\\d{8}", message="Must be 8 digit code")
    private String healthInsuranceId;

    @ValidDateValidation(value="yyyy-MM-dd", message="Date of birth must be formatted yyyy-MM-dd")
    // Date has to be in yyyy-MM-dd format
    private String dateOfBirth;
}