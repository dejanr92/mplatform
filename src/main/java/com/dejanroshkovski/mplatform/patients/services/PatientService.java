package com.dejanroshkovski.mplatform.patients.services;

import java.util.ArrayList;
import java.util.List;

import com.dejanroshkovski.mplatform.patients.domain.Patient;
import com.dejanroshkovski.mplatform.patients.dto.PatientDTO;
import com.dejanroshkovski.mplatform.patients.dto.PatientPatchDTO;
import com.dejanroshkovski.mplatform.patients.dto.PatientResponseDTO;
import com.dejanroshkovski.mplatform.patients.repositories.PatientRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService{

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PatientResponseDTO getPatientById(Integer id){
        Patient patient = patientRepository.getOne(id);
        return modelMapper.map(patient, PatientResponseDTO.class);
    }
    public PatientDTO storePatient(PatientDTO patientDTO){
        Patient Patient = modelMapper.map(patientDTO, Patient.class);
        Patient storedPatient = patientRepository.save(Patient);
        
        return modelMapper.map(storedPatient, PatientDTO.class);
    }
    public void deletePatientById(Integer id){
        patientRepository.deleteById(id);
    }
    public PatientDTO updatePatientById(PatientPatchDTO patientPatchDTO, Integer id){
        Patient currentPatient = patientRepository.getOne(id);

        // Skip null fields Patch request
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(patientPatchDTO, currentPatient);

        Patient storedPatient = patientRepository.save(currentPatient);
        // You can use main DTO now patch was only for update to skip @NotNull validations
        return modelMapper.map(storedPatient, PatientDTO.class);
    }
    public List<PatientResponseDTO> findByFirstOrLastName(String searchString){

        List<Patient> patientList = patientRepository.findByFirstOrLastName(searchString);
        List<PatientResponseDTO> patientDTOList = new ArrayList<>();

        for (Patient patient : patientList) {
            PatientResponseDTO currentResponseDTO = modelMapper.map(patient, PatientResponseDTO.class);
            patientDTOList.add(currentResponseDTO);
        }
        
        return patientDTOList;
    }
}