package com.employeeService.employeeService.services;


import com.employeeService.employeeService.model.dto.EmployeeDto;
import com.employeeService.employeeService.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDto Saved(EmployeeDto employeeDto);
    EmployeeDto UpdateEmployee(Long id , EmployeeDto employeeDto);
    EmployeeDto getSinglEmp(Long id);

    public String DeleteEmployee(Long id);
    List<EmployeeDto> GetAllEmployees();


}
