package com.tl.pr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeWarning {

    @Id
    @SequenceGenerator(
            name = "warning_sequence",
            sequenceName = "warning_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "warning_sequence"
    )
    private Long warningId;

    private int warningCount;

    private Long employeeId;

}
