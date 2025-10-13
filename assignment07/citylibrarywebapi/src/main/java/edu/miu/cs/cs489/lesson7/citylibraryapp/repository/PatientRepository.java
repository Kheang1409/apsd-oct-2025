package edu.miu.cs.cs489.lesson7.citylibraryapp.repository;

import edu.miu.cs.cs489.lesson7.citylibraryapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByLastNameContainingIgnoreCaseOrderByLastNameAsc(String lastName);

    List<Patient> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String firstName, String lastName, String email);
}
