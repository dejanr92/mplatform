package com.dejanroshkovski.mplatform.examinations.dto;

import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class ExaminationPatchDTO{

    private Integer id;

    private String sympthoms;

    private String diagnosis;

    @Positive
    private Integer doctorId;

    @Positive
    private Integer patientId;

    private String examinationDate;

}