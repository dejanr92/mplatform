package com.dejanroshkovski.mplatform.doctors.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class DoctorPatchDTO{

    private Integer id;

    private String firstName;

    private String lastName;

    @Email(message="Must provide valid email")
    private String email;

    // Identification must abide the following rule:
    // Starts with DC or DN and has 7 numbers

    // StartsWithValidation(value="DC")
    @Pattern(regexp="(DC|DN)\\d{7}", message="Must start with DC or DN and contain 7 digits")
    private String doctorIdentification;
}