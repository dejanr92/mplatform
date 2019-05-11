package com.dejanroshkovski.mplatform.examinations.domain;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dejanroshkovski.mplatform.doctors.domain.Doctor;
import com.dejanroshkovski.mplatform.patients.domain.Patient;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
@Table(name="examinations")
public class Examination{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="sympthoms")
    private String sympthoms;

    @Column(name="diagnosis")
    private String diagnosis;

    // Don't use cascade delete
    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="patient_id", referencedColumnName="id")
    @JsonBackReference
    private Patient patient;

    // ManyToOne
    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="doctor_id", referencedColumnName="id")
    @JsonBackReference
    private Doctor doctor;

    // Date of the visit
    @Column(name="examination_date")
    private Date examinationDate;

}