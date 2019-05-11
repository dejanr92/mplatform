package com.dejanroshkovski.mplatform.examinations.dto;

import java.sql.Date;

import com.dejanroshkovski.mplatform.doctors.domain.Doctor;
import com.dejanroshkovski.mplatform.patients.domain.Patient;

import lombok.Data;

@Data
public class ExaminationResponseDTO{

    private Integer id;

    private String sympthoms;

    private String diagnosis;

    private Doctor doctor;

    private Patient patient;

    private Date examinationDate;

    private Integer patientAge;

}