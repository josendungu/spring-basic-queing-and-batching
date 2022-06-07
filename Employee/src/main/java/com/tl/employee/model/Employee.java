package com.tl.employee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "emailId_unique",
                columnNames = "email_address"
        )
)
public class Employee {
    @Id
    @SequenceGenerator(
            sequenceName = "employee_sequence",
            allocationSize = 1,
            name = "employee_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long employeeId;

    @Column(
            nullable = false,
            length = 50
    )
    private String firstName;

    @Column(
            nullable = false,
            length = 50
    )
    private String lastName;

    @Column(
            nullable = false,
            unique = true,
            name = "email_address"
    )
    private String email;

    private String phoneNumber;


}
