package com.tl.pr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "performance_review")
public class Review {

    @Id
    @SequenceGenerator(
            sequenceName = "pr_sequence",
            allocationSize = 1,
            name = "pr_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pr_sequence"
    )
    private Long pRId;

    private Long employeeId;

    private LocalDate placedDate;

    private String paramOne = "One";

    private String paramTwo = "Two";
}
