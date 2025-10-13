package edu.miu.cs.cs489.lesson7.citylibraryapp.controller;

import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientRequest;
import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientResponse;
import edu.miu.cs.cs489.lesson7.citylibraryapp.exception.PatientNotFoundException;
import edu.miu.cs.cs489.lesson7.citylibraryapp.model.Patient;
import edu.miu.cs.cs489.lesson7.citylibraryapp.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/adsweb/api/v1")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/patients")
    public ResponseEntity<List<PatientResponse>> listPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping(value = "/patients/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Integer patientId) throws PatientNotFoundException {
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

    @PostMapping(value = "/patients")
    public ResponseEntity<PatientResponse> registerNewPatient(@Valid @RequestBody PatientRequest patientRequest) {
        return new ResponseEntity<>(patientService.addNewPatient(patientRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/patient/{patientId}", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientRequest> updatePatient(@PathVariable Integer patientId, @RequestBody edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientRequest editedPatient) {
        // Delegate mapping to service; return the received DTO for confirmation
        patientService.updatePatient(patientId, editedPatient);
        return new ResponseEntity<>(editedPatient, HttpStatus.OK);
    }

    @DeleteMapping(value = "/patient/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer patientId) {
        patientService.deletePatientById(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/patient/search/{searchString}")
    public ResponseEntity<List<Patient>> searchPatient(@PathVariable String searchString) {
        return ResponseEntity.ok(patientService.searchPatient(searchString));
    }
}
