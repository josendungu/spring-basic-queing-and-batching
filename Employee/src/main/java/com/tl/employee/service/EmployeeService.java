package com.tl.employee.service;

import com.tl.employee.model.Employee;
import com.tl.employee.model.EmployeeRegistrationRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    List<Employee> fetchAllEmployees();

    Employee registerEmployee(EmployeeRegistrationRequest employee);

    Boolean placeEmployeeOnReview(Long employeeId);

    Boolean incrementEmployeeWarningCount(Long employeeId);

    Boolean placeEmployeeOnPerformanceReview(Long employeeId);

    void save(MultipartFile file);
}
