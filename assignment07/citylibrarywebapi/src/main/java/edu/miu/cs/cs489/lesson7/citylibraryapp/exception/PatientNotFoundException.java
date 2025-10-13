package edu.miu.cs.cs489.lesson7.citylibraryapp.exception;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}
