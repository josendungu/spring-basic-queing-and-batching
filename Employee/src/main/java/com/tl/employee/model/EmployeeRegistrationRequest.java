package com.tl.employee.model;

//you get to string, to equal for free as compared to a normal class where you dont
public record EmployeeRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
