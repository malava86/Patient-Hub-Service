package com.straumann.patienthub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.straumann.patienthub.model.Patient;
import java.util.List;
import java.util.Optional;

import com.straumann.patienthub.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	
	public List<Patient> getAllPatients(){
		return patientRepository.findAll();
	}

	@Cacheable(value = "patients", key="#id")
	public Optional<Patient> getPatientById(Long id){
		return patientRepository.findById(id);
	}
	
	public Patient createPatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	public void deletePatient(Long id) {
		patientRepository.deleteById(id);
	}
}
