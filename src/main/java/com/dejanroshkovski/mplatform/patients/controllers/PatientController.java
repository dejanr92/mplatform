package com.dejanroshkovski.mplatform.patients.controllers;

import java.util.List;

import javax.validation.Valid;

import com.dejanroshkovski.mplatform.patients.dto.PatientDTO;
import com.dejanroshkovski.mplatform.patients.dto.PatientPatchDTO;
import com.dejanroshkovski.mplatform.patients.dto.PatientResponseDTO;
import com.dejanroshkovski.mplatform.patients.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/patient")
public class PatientController{

    @Autowired
    PatientService patientService;

    @GetMapping(value="/get/{id}", produces="application/json")
    public ResponseEntity<PatientResponseDTO> getPatientInfo(@PathVariable Integer id) 
    throws Exception{
        PatientResponseDTO patientDTO = patientService.getPatientById(id);
        return ResponseEntity.ok(patientDTO);
    }
    @PostMapping(value="/store", consumes="application/json", produces="application/json")
    public ResponseEntity<PatientDTO> storePatient(@Valid @RequestBody PatientDTO patientDTO) 
    throws Exception{
        PatientDTO storedPatient = patientService.storePatient(patientDTO);
        return ResponseEntity.ok(storedPatient);
    }
    @DeleteMapping(value="/delete/{id}", produces="application/json")
    public ResponseEntity<String> deletePatient(@PathVariable Integer id)
    throws Exception{
        patientService.deletePatientById(id);
        return ResponseEntity.ok("ok");
    }
    @PatchMapping(value="update/{id}", consumes="application/json", produces="application/json")
    public ResponseEntity<PatientDTO> putMethodName(@PathVariable Integer id, @Valid @RequestBody PatientPatchDTO patientPatchDTO) 
    throws Exception{
        PatientDTO storedPatient = patientService.updatePatientById(patientPatchDTO, id);
        return ResponseEntity.ok(storedPatient);
    }
    @GetMapping(value="/findByName/{inputString}", produces="application/json")
    public ResponseEntity<List<PatientResponseDTO>> findByFirstOrLastName(@PathVariable String inputString)
    throws Exception{
        List<PatientResponseDTO> foundPatients = patientService.findByFirstOrLastName(inputString);
        return ResponseEntity.ok(foundPatients);
    } 
}