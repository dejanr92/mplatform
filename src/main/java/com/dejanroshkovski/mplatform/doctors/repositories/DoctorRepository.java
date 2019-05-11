package com.dejanroshkovski.mplatform.doctors.repositories;

import java.util.List;

import com.dejanroshkovski.mplatform.doctors.domain.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

    @Query(value = "SELECT d FROM Doctor d WHERE d.lastName LIKE %:inputValue% OR d.firstName LIKE %:inputValue%")
    public List<Doctor> findByFirstOrLastName(@Param("inputValue") String inputValue);

    @Query(value = "SELECT d FROM Doctor d WHERE d.email LIKE %:inputValue%")
    public List<Doctor> findByEmail(@Param("inputValue") String inputValue);

}