package com.tl.employee.controller;


import com.tl.employee.helper.ExcelHelper;
import com.tl.employee.model.Employee;
import com.tl.employee.model.EmployeeRegistrationRequest;
import com.tl.employee.model.ResponseMessage;
import com.tl.employee.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> fetchAllEmployees() {
        return employeeService.fetchAllEmployees();
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody EmployeeRegistrationRequest employee) {
        return employeeService.registerEmployee(employee);
    }

    @PostMapping("/warning/{employeeId}")
    public String addEmployeeWarning(@PathVariable("employeeId") Long employeeId){
        employeeService.incrementEmployeeWarningCount(employeeId);
        return "Adding employee warning Count";
    }


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                employeeService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                log.info(e.toString());
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


}
