package com.employeeService.employeeService.model.entity;

import com.commonLibraryService.commonService.entity.AuditableEntity;
import jakarta.persistence.*;

@Entity
@Table(name="employees")
public class Employee extends  AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empName;
    private String empEmail;
    private String empCode;
    private String companyName;


    // Getters and Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmpName() {
        return empName;
    }

    public Long getId() {
        return id;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public String getEmpCode() {
        return empCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "Employee{"+
                "id="+id+
                ", empName='"+empName+'\''+
                ", empEmail='"+empEmail+'\''+
                ", empCode='"+empCode+'\''+
                ",companyName='"+companyName+'\''+
                '}';
    }
}
