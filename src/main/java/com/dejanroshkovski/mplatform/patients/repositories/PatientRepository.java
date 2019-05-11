package com.dejanroshkovski.mplatform.patients.repositories;

import java.util.List;

import com.dejanroshkovski.mplatform.patients.domain.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

    @Query(value = "SELECT p FROM Patient p WHERE p.lastName LIKE %:inputValue% OR p.firstName LIKE %:inputValue%")
    public List<Patient> findByFirstOrLastName(@Param("inputValue") String inputValue);
    
}