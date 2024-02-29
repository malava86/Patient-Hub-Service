package com.straumann.patienthub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.straumann.patienthub.exception.ResourceNotFoundException;
import com.straumann.patienthub.model.Patient;
import com.straumann.patienthub.service.PatientService;

@RestController
@RequestMapping("api/patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping
	public List<Patient> getAllPatients(){
		return patientService.getAllPatients();
	}
	
	@GetMapping("/{id}")
	public Patient getPatientById(@PathVariable Long id) {
		Optional<Patient> patient = patientService.getPatientById(id);
		return patient.orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
	}
	
	@PostMapping
	public Patient createPatient(@RequestBody Patient patient) {
		return patientService.createPatient(patient);
	}
	
	@DeleteMapping("/{id}")
	public void deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
	}
	

}
