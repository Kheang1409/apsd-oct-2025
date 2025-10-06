package apsd.quiz1.Utility;
import java.util.List;

import apsd.quiz1.DTOs.DepartmentDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Export {

    static public String exportDataAsJSON(List<DepartmentDto> departments){
        if (departments == null) return "null";

        ObjectMapper mapper = new ObjectMapper();
        // support java.time types if any, and make dates human-readable
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            if (departments.size() == 1) {
                // if caller passed a single department in the list, return an object like your sample
                return writer.writeValueAsString(departments.get(0));
            }
            // otherwise return the list (JSON array)
            return writer.writeValueAsString(departments);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to export data as JSON", e);
        }
    }
}
