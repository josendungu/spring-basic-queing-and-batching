package com.tl.pr.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private Long employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String paramOne = "One";

    private String paramTwo = "Two";

}
