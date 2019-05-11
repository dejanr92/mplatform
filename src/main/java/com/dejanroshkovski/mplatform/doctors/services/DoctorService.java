package com.dejanroshkovski.mplatform.doctors.services;

import java.util.ArrayList;
import java.util.List;

import com.dejanroshkovski.mplatform.doctors.domain.Doctor;
import com.dejanroshkovski.mplatform.doctors.dto.DoctorDTO;
import com.dejanroshkovski.mplatform.doctors.dto.DoctorPatchDTO;
import com.dejanroshkovski.mplatform.doctors.repositories.DoctorRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService{

    @Autowired
    DoctorRepository doctorRepository;

    // Injecting model mapper
    @Autowired
    private ModelMapper modelMapper;

    public Doctor getDoctorById(Integer id){
        return doctorRepository.getOne(id);
    }
    public DoctorDTO storeDoctor(DoctorDTO doctorDTO){
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        Doctor storedDoctor = doctorRepository.save(doctor);
        
        return modelMapper.map(storedDoctor, DoctorDTO.class);
    }
    public void deleteDoctorById(Integer id){
        doctorRepository.deleteById(id);
    }
    public DoctorDTO updateDoctorById(DoctorPatchDTO doctorPatchDTO, Integer id){
        Doctor currentDoctor = doctorRepository.getOne(id);

        // Skip null fields Patch request
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(doctorPatchDTO, currentDoctor);

        Doctor storedDoctor = doctorRepository.save(currentDoctor);
        // You can use main DTO now patch was only for update to skip @NotNull validations
        return modelMapper.map(storedDoctor, DoctorDTO.class);
    }
    public List<DoctorDTO> findByFirstOrLastName(String searchString){

        List<Doctor> doctorList = doctorRepository.findByFirstOrLastName(searchString);
        List<DoctorDTO> doctorDTOList = new ArrayList<>();

        for (Doctor doctor : doctorList) {
            DoctorDTO currentDTO = modelMapper.map(doctor, DoctorDTO.class);
            doctorDTOList.add(currentDTO);
        }
        
        return doctorDTOList;
    }
    public List<DoctorDTO> findByEmail(String searchString){

        List<Doctor> doctorList = doctorRepository.findByEmail(searchString);
        List<DoctorDTO> doctorDTOList = new ArrayList<>();

        for (Doctor doctor : doctorList) {
            DoctorDTO currentDTO = modelMapper.map(doctor, DoctorDTO.class);
            doctorDTOList.add(currentDTO);
        }
        
        return doctorDTOList;
    }
}