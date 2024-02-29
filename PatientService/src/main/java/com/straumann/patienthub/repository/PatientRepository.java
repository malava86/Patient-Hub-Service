package com.straumann.patienthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.straumann.patienthub.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
