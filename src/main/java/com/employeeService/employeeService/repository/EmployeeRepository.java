package com.employeeService.employeeService.repository;

import com.employeeService.employeeService.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    <Optional> Employee findByEmpCodeAndCompanyName(String empCode , String companyName);
}
