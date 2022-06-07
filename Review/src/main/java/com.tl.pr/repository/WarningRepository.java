package com.tl.pr.repository;

import com.tl.pr.model.EmployeeWarning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarningRepository extends JpaRepository<EmployeeWarning, Long> {

    @Query("SELECT w FROM EmployeeWarning w WHERE w.employeeId = ?1")
    Optional<EmployeeWarning> fetchWarningByEmployeeId(Long employeeId);

}
