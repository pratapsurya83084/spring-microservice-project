package com.employeeService.employeeService.controller;

import com.employeeService.employeeService.model.dto.EmployeeDto;
import com.employeeService.employeeService.services.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {


    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("save")
    public ResponseEntity<EmployeeDto> saveEmp(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto emp = employeeService.Saved(employeeDto);

        return new ResponseEntity<>(emp, HttpStatus.CREATED);

    }


    @GetMapping("getsingle-emp")
    public EmployeeDto GetSingleEmp(@RequestParam Long id) {
        EmployeeDto emp = employeeService.getSinglEmp(id);
        return emp;

    }

    @PutMapping("update-emp")
    public EmployeeDto UpdateEmp(@RequestParam Long id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto emp = employeeService.UpdateEmployee(id, employeeDto);
        return emp;
    }

    @GetMapping("getall-emp")
    public List<EmployeeDto> getAllemp() {
        return employeeService.GetAllEmployees();
    }

    //find employee by empCode and companyName
    @GetMapping("getemployeeByempCodeAndcompanyName")
    public EmployeeDto findByempCodeAndCompanyName(@RequestParam  String empCode ,@RequestParam String companyName){

        return employeeService.findByempCodeAndCompanyName(empCode ,companyName);
    }


    @DeleteMapping("delete-emp")
    public String deleteEmp(@RequestParam Long id){
      String emp =  employeeService.DeleteEmployee(id);
     return  emp;
    }

}
