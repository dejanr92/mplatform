package com.dejanroshkovski.mplatform.patients.dto;

import java.util.List;

import com.dejanroshkovski.mplatform.examinations.domain.Examination;

import lombok.Data;

@Data
public class PatientResponseDTO{

    private Integer id;

    private String firstName;

    private String lastName;

    private String healthInsuranceId;

    private String dateOfBirth;
    
    private List<Examination> examinations;
}