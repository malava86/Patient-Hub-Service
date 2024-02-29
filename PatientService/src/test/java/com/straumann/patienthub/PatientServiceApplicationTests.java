package com.straumann.patienthub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.straumann.patienthub.model.Patient;
import com.straumann.patienthub.repository.PatientRepository;
import com.straumann.patienthub.service.PatientService;

@RunWith(SpringRunner.class)
@SpringBootTest
class PatientServiceApplicationTests {
	
	@InjectMocks
	private PatientService patientService;
	
	@Mock
    private PatientRepository patientRepository;
	
	@Test
	public void testGetPatientById() {
	
		 //Mocking repository
		 PatientRepository patientRepository = mock(PatientRepository.class);
		 when(patientRepository.findById(1L)).thenReturn(java.util.Optional.of(new Patient()));
		 
		 Long id = 1L;
		 Optional<Patient> patient = patientService.getPatientById(id);
		 assertNotNull(patient);
		 assertEquals(id, patient.get().getId());
	}
	
	@Test
    public void testGetPatientById_CacheHit() {
        // Given
        Long id = 1L;
        Patient patient = new Patient();
        patient.setId(id);
        when(patientRepository.findById(id)).thenReturn(java.util.Optional.of(patient));

        // When
        Optional<Patient> result1 = patientService.getPatientById(id);
        Optional<Patient> result2 = patientService.getPatientById(id);

        // Then
        assertEquals(patient, result1);
        assertEquals(patient, result2);
        verify(patientRepository, times(1)).findById(id); // Make sure repository method is invoked only once
    }

    @Test
    public void testGetPatientById_CacheMiss() {
        // Given
        Long id = 1L;
        Patient patient = new Patient();
        patient.setId(id);
        when(patientRepository.findById(id)).thenReturn(java.util.Optional.of(patient));

        // When
        Optional<Patient> result = patientService.getPatientById(id);

        // Then
        assertEquals(patient, result);
        verify(patientRepository, times(1)).findById(id); // Make sure repository method is invoked
    }

	@Test
	void contextLoads() {
	}

}
