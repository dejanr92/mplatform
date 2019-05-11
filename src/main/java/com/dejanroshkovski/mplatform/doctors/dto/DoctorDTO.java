package com.dejanroshkovski.mplatform.doctors.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class DoctorDTO{

    private Integer id;

    @NotEmpty(message="firstName cannot be empty")
    private String firstName;

    @NotEmpty(message="lastName cannot be empty")
    private String lastName;

    @NotEmpty(message="Must provide valid email")
    @Email(message="Must provide valid email")
    private String email;

    // Identification must abide the following rule:
    // Starts with DC or DN and has 7 numbers

    // StartsWithValidation(value="DC")
    @NotEmpty(message="Must provide valid doctorIdentification")
    @Pattern(regexp="(DC|DN)\\d{7}", message="Must start with DC or DN and contain 7 digits")
    private String doctorIdentification;
}