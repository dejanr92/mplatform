package com.dejanroshkovski.mplatform.doctors.controllers;

import java.util.List;

import javax.validation.Valid;

import com.dejanroshkovski.mplatform.doctors.domain.Doctor;
import com.dejanroshkovski.mplatform.doctors.dto.DoctorDTO;
import com.dejanroshkovski.mplatform.doctors.dto.DoctorPatchDTO;
import com.dejanroshkovski.mplatform.doctors.services.DoctorService;

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
@RequestMapping("/api/doctor")
public class DoctorController{

    @Autowired
    DoctorService doctorService;

    @GetMapping(value="/get/{id}", produces="application/json")
    public ResponseEntity<Doctor> getDoctorInfo(@PathVariable Integer id) 
    throws Exception{
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }
    @PostMapping(value="/store", consumes="application/json", produces="application/json")
    public ResponseEntity<DoctorDTO> storeDoctor(@Valid @RequestBody DoctorDTO doctorDTO) 
    throws Exception{
        DoctorDTO storedDoctor = doctorService.storeDoctor(doctorDTO);
        return ResponseEntity.ok(storedDoctor);
    }
    @DeleteMapping(value="/delete/{id}", produces="application/json")
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer id)
    throws Exception{
        doctorService.deleteDoctorById(id);
        return ResponseEntity.ok("ok");
    }
    @PatchMapping(value="update/{id}", consumes="application/json", produces="application/json")
    public ResponseEntity<DoctorDTO> putMethodName(@PathVariable Integer id, @Valid @RequestBody DoctorPatchDTO doctorPatchDTO) 
    throws Exception{
        DoctorDTO storedDoctor = doctorService.updateDoctorById(doctorPatchDTO, id);
        return ResponseEntity.ok(storedDoctor);
    }
    @GetMapping(value="/findByName/{inputString}", produces="application/json")
    public ResponseEntity<List<DoctorDTO>> findByFirstOrLastName(@PathVariable String inputString)
    throws Exception{
        List<DoctorDTO> foundDoctors = doctorService.findByFirstOrLastName(inputString);
        return ResponseEntity.ok(foundDoctors);
    } 
    @GetMapping(value="/findByEmail/{inputString}", produces="application/json")
    public ResponseEntity<List<DoctorDTO>> findByEmail(@PathVariable String inputString)
    throws Exception{
        List<DoctorDTO> foundDoctors = doctorService.findByEmail(inputString);
        return ResponseEntity.ok(foundDoctors);
    } 
}