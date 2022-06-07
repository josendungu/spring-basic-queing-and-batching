package com.tl.employee.service;


import com.tl.employee.constants.Constants;
import com.tl.employee.dto.EmployeeDto;
import com.tl.employee.helper.ExcelHelper;
import com.tl.employee.model.Employee;
import com.tl.employee.model.EmployeeRegistrationRequest;
import com.tl.employee.repository.EmployeeRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final RabbitTemplate template;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(RabbitTemplate template, EmployeeRepository employeeRepository) {
        this.template = template;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> fetchAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee registerEmployee(EmployeeRegistrationRequest request) {

        Employee employee = Employee.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .build();
        return employeeRepository.save(employee);
    }

    @Override
    public Boolean placeEmployeeOnReview(Long employeeId) {
        return null;
    }

    @Override
    public Boolean incrementEmployeeWarningCount(Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmployeeId(employeeId);

        if (employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();

            EmployeeDto employeeDto = EmployeeDto.builder()
                    .employeeId(employee.getEmployeeId())
                    .email(employee.getEmail())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .phoneNumber(employee.getPhoneNumber())
                    .paramOne("One")
                    .paramTwo("Two")
                    .build();

            template.convertAndSend(Constants.EXCHANGE, Constants.ROUTING_KEY, employeeDto);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean placeEmployeeOnPerformanceReview(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmployeeId(employeeId);

        if (employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();

            EmployeeDto employeeDto = EmployeeDto.builder()
                    .employeeId(employee.getEmployeeId())
                    .email(employee.getEmail())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .phoneNumber(employee.getPhoneNumber())
                    .build();

            template.convertAndSend(Constants.EXCHANGE, Constants.ROUTING_KEY, employeeDto);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<Employee> users = ExcelHelper.getUsersFromExcel(file.getInputStream());
            employeeRepository.saveAll(users);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }


}
