package com.dejanroshkovski.mplatform.patients.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dejanroshkovski.mplatform.examinations.domain.Examination;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
@Table(name="patients")
public class Patient{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="health_insurance_id")
    private String healthInsuranceId;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    // Should also cascade delete examinations when patient is deleted
    // leave default fetchtype
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="patient")
    @JsonBackReference
    private List<Examination> examinations;

}