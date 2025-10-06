package apsd.quiz1.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
// ...existing imports...

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import apsd.quiz1.Domain.Department;
import apsd.quiz1.Domain.Employee;

public class Export {

    static public String exportDataAsJSON(List<Department> departments){
        if (departments == null) return "[]";

        // Build output as list of departments matching the requested shape
        List<Map<String, Object>> out = new ArrayList<>();
        for (Department d : departments) {
            if (d == null) continue;
            Map<String, Object> deptMap = new LinkedHashMap<>();
            deptMap.put("departmentNo", d.getDepartmentNo());
            deptMap.put("name", d.getName());
            deptMap.put("totalSalary", d.getTotalSalary());
            Employee head = d.getHeadOfEmployee();
            if (head != null) {
                Map<String, Object> headMap = new LinkedHashMap<>();
                headMap.put("employeeNo", head.getEmployeeNo());
                headMap.put("firstName", head.getFirstName());
                headMap.put("lastName", head.getLastName());
                deptMap.put("headOfEmployee", headMap);
            } else {
                deptMap.put("headOfEmployee", null);
            }

            List<Map<String, Object>> deptEmps = new ArrayList<>();
            if (d.getEmployees() != null) {
                for (Employee de : d.getEmployees()) {
                    if (de == null) continue;
                    Map<String, Object> deMap = new LinkedHashMap<>();
                    deMap.put("employeeNo", de.getEmployeeNo());
                    deMap.put("firstName", de.getFirstName());
                    deMap.put("lastName", de.getLastName());
                    deMap.put("yearsOfEmployment", de.getYearsOfEmployment());
                    deptEmps.add(deMap);
                }
            }
            deptMap.put("employees", deptEmps);

            out.add(deptMap);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(out);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "[]";
        }
    }
}
