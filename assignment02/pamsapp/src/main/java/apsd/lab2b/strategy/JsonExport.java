package apsd.lab2b.strategy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import apsd.lab2b.domain.Patient;

public class JsonExport implements IReportStrategy {
    private ObjectMapper mapper = new ObjectMapper();

    public JsonExport() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public String generateReport(List<Patient> patients) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(patients);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile(List<Patient> patients, String fileName) {
        try {
            File file = new File(fileName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, patients);
            System.out.println("Patient data written to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + fileName, e);
        }
    }
}
