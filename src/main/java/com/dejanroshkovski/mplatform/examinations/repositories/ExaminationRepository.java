package com.dejanroshkovski.mplatform.examinations.repositories;

import java.util.List;

import com.dejanroshkovski.mplatform.examinations.domain.Examination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Integer>{

    @Query(value = "SELECT e FROM Examination e WHERE e.sympthoms LIKE %:inputValue%")
    public List<Examination> findBySympthoms(@Param("inputValue") String inputValue);

    @Query(value = "SELECT e FROM Examination e WHERE e.diagnosis LIKE %:inputValue%")
    public List<Examination> findByDiagnosis(@Param("inputValue") String inputValue);

}