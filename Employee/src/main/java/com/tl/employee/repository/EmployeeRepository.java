package com.tl.employee.repository;


import com.tl.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query("SELECT e FROM Employee e WHERE e.employeeId = ?1")
    Optional<Employee> findEmployeeByEmployeeId(Long employeeID);

}
