package com.dejanroshkovski.mplatform.examinations.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.dejanroshkovski.mplatform.util.validations.ValidDateValidation;

import lombok.Data;

@Data
public class ExaminationDTO{

    private Integer id;

    @NotEmpty(message="sympthoms cannot be empty")
    private String sympthoms;

    @NotEmpty(message="diagnosis cannot be empty")
    private String diagnosis;

    @Positive
    @NotNull(message="Must provide doctor id")
    private Integer doctorId;

    @Positive
    @NotNull(message="Must provide patient id")
    private Integer patientId;

    @NotNull(message="Must provide examination Date")
    @ValidDateValidation(value="yyyy-MM-dd", message="Must be formatted yyyy-MM-dd")
    // Date has to be in yyyy-MM-dd format
    private String examinationDate;

}