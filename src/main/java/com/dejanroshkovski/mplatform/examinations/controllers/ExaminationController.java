package com.dejanroshkovski.mplatform.examinations.controllers;

import java.util.List;

import javax.validation.Valid;

import com.dejanroshkovski.mplatform.examinations.dto.ExaminationDTO;
import com.dejanroshkovski.mplatform.examinations.dto.ExaminationPatchDTO;
import com.dejanroshkovski.mplatform.examinations.dto.ExaminationResponseDTO;
import com.dejanroshkovski.mplatform.examinations.services.ExaminationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/examination")
public class ExaminationController{

    @Autowired
    ExaminationService examinationService;

    @GetMapping(value="/get/{id}", produces="application/json")
    public ResponseEntity<ExaminationResponseDTO> getExaminationInfo(@PathVariable Integer id) 
    throws Exception{
        ExaminationResponseDTO examinationResponseDTO = examinationService.getExaminationById(id);
        return ResponseEntity.ok(examinationResponseDTO);
    }

    @PostMapping(value="/store", consumes="application/json", produces="application/json")
    public ResponseEntity<ExaminationDTO> storeExamination(@Valid @RequestBody ExaminationDTO examinationDTO) 
    throws Exception{
        ExaminationDTO storedExamination = examinationService.storeExamination(examinationDTO);
        return ResponseEntity.ok(storedExamination);
    }

    @DeleteMapping(value="/delete/{id}", produces="application/json")
    public ResponseEntity<String> deleteExamination(@PathVariable Integer id)
    throws Exception{
        examinationService.deleteExaminationById(id);
        return ResponseEntity.ok("ok");
    }

    @PatchMapping(value="update/{id}", consumes="application/json", produces="application/json")
    public ResponseEntity<ExaminationDTO> putMethodName(@PathVariable Integer id, @Valid @RequestBody ExaminationPatchDTO examinationPatchDTO) 
    throws Exception{
        ExaminationDTO storedExamination = examinationService.updateExaminationById(examinationPatchDTO, id);
        return ResponseEntity.ok(storedExamination);
    }

    @GetMapping(value="/findBySympthoms/{inputString}", produces="application/json")
    public ResponseEntity<List<ExaminationResponseDTO>> findBySympthoms(@PathVariable String inputString)
    throws Exception{
        List<ExaminationResponseDTO> foundExaminations = examinationService.findBySympthoms(inputString);
        return ResponseEntity.ok(foundExaminations);
    }

    @GetMapping(value="/findByDiagnosis/{inputString}", produces="application/json")
    public ResponseEntity<List<ExaminationResponseDTO>> findByDiagnosis(@PathVariable String inputString)
    throws Exception{
        List<ExaminationResponseDTO> foundExaminations = examinationService.findByDiagnosis(inputString);
        return ResponseEntity.ok(foundExaminations);
    } 
}