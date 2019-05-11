package com.dejanroshkovski.mplatform.examinations.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.dejanroshkovski.mplatform.doctors.domain.Doctor;
import com.dejanroshkovski.mplatform.doctors.repositories.DoctorRepository;
import com.dejanroshkovski.mplatform.examinations.domain.Examination;
import com.dejanroshkovski.mplatform.examinations.dto.ExaminationDTO;
import com.dejanroshkovski.mplatform.examinations.dto.ExaminationPatchDTO;
import com.dejanroshkovski.mplatform.examinations.dto.ExaminationResponseDTO;
import com.dejanroshkovski.mplatform.examinations.repositories.ExaminationRepository;
import com.dejanroshkovski.mplatform.patients.domain.Patient;
import com.dejanroshkovski.mplatform.patients.repositories.PatientRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminationService{

    @Autowired
    ExaminationRepository examinationRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ExaminationResponseDTO getExaminationById(Integer id){
        Examination examination = examinationRepository.getOne(id);
        ExaminationResponseDTO examinationResponseDTO = modelMapper.map(examination, ExaminationResponseDTO.class);
        // Get examination needs to calculate the patient age
        examinationResponseDTO.setPatientAge(calculatePatientAge(examination));

        return examinationResponseDTO;
    }
    public ExaminationDTO storeExamination(ExaminationDTO examinationDTO){

        // TODO Mapper won't remap these, it may be good idea to bring in a mapping layer
        Doctor doctor = doctorRepository.getOne(examinationDTO.getDoctorId());
        Patient patient = patientRepository.getOne(examinationDTO.getPatientId());

        Examination examination = modelMapper.map(examinationDTO, Examination.class);
        examination.setDoctor(doctor);
        examination.setPatient(patient);

        Examination storedExamination = examinationRepository.save(examination);
        
        return modelMapper.map(storedExamination, ExaminationDTO.class);
    }
    public void deleteExaminationById(Integer id){
        examinationRepository.deleteById(id);
    }
    public ExaminationDTO updateExaminationById(ExaminationPatchDTO examinationPatchDTO, Integer id){
        Examination currentExamination = examinationRepository.getOne(id);

        // Skip null fields Patch request
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(examinationPatchDTO, currentExamination);
        if(examinationPatchDTO.getDoctorId() != null){
            Doctor doctor = doctorRepository.getOne(examinationPatchDTO.getDoctorId());
            currentExamination.setDoctor(doctor);
        }
        if(examinationPatchDTO.getPatientId() != null){
            Patient patient = patientRepository.getOne(examinationPatchDTO.getPatientId());
            currentExamination.setPatient(patient);
        }

        Examination storedExamination = examinationRepository.save(currentExamination);
        // You can use main DTO now patch was only for update to skip @NotNull validations
        return modelMapper.map(storedExamination, ExaminationDTO.class);
    }
    public List<ExaminationResponseDTO> findBySympthoms(String searchString){

        List<Examination> examinationList = examinationRepository.findBySympthoms(searchString);
        List<ExaminationResponseDTO> examinationDTOList = new ArrayList<>();

        for (Examination examination : examinationList) {
            ExaminationResponseDTO currentDTO = modelMapper.map(examination, ExaminationResponseDTO.class);
            currentDTO.setPatientAge(calculatePatientAge(examination));
            examinationDTOList.add(currentDTO);
        }
        
        return examinationDTOList;
    }
    public List<ExaminationResponseDTO> findByDiagnosis(String searchString){

        List<Examination> examinationList = examinationRepository.findByDiagnosis(searchString);
        List<ExaminationResponseDTO> examinationDTOList = new ArrayList<>();

        for (Examination examination : examinationList) {
            ExaminationResponseDTO currentDTO = modelMapper.map(examination, ExaminationResponseDTO.class);
            currentDTO.setPatientAge(calculatePatientAge(examination));
            examinationDTOList.add(currentDTO);
        }
        
        return examinationDTOList;
    }
    public Integer calculatePatientAge(Examination examination){
        
        LocalDate dateOfBirth = LocalDate.parse(examination.getPatient().getDateOfBirth().toString());
        LocalDate parsedExamDate = LocalDate.parse(examination.getExaminationDate().toString());
        Integer calculatedAge = Period.between(dateOfBirth, parsedExamDate).getYears();

        return calculatedAge;
    }
}