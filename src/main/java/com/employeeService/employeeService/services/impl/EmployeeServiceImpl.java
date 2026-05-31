package com.employeeService.employeeService.services.impl;

import com.commonLibraryService.commonService.exception.BadRequestException;
import com.commonLibraryService.commonService.exception.ResourceNotFoundException;
import com.employeeService.employeeService.client.AddressClient;
import com.employeeService.employeeService.config.AppConfig;
//import com.employeeService.employeeService.exception.BadRequestException;
//import com.employeeService.employeeService.exception.ResourceNotFoundException;
import com.employeeService.employeeService.model.dto.AddressDto;
import com.employeeService.employeeService.model.dto.EmployeeDto;
import com.employeeService.employeeService.model.entity.Employee;
import com.employeeService.employeeService.repository.EmployeeRepository;
import com.employeeService.employeeService.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    //inject bean of aaddress Client
    private final AddressClient addressClient;
     private final  ModelMapper modelMapper;
      private  final EmployeeRepository employeeRepository;

      EmployeeServiceImpl(EmployeeRepository employeeRepository , ModelMapper modelMapper , AddressClient addressClient){
          this.employeeRepository = employeeRepository;
          this.modelMapper = modelMapper;
          this.addressClient = addressClient;
      }



    @Override
    public EmployeeDto Saved(EmployeeDto employeeDto) {
          // check employee id if exist show error exception
          if (employeeDto.getId()!=null) {
              throw new RuntimeException("employee already exists");
          }
          //convert dto to entity
       Employee entity = modelMapper.map(employeeDto , Employee.class);
          entity.setCreatedAt(LocalDateTime.now());

          Employee SavedEntity  = employeeRepository.save(entity);
          return modelMapper.map(SavedEntity , EmployeeDto.class);
      }

    @Override
    public String DeleteEmployee(Long id) {
     if (id==null){
     throw  new BadRequestException("please provide Employee Id");

    }

        if(employeeRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Employee not found");
        }

        employeeRepository.deleteById(id);
     return "employee delete Successfull";
    }

    @Override
    public List<EmployeeDto> GetAllEmployees() {
      List<Employee>  employees  =  employeeRepository.findAll();
      if (employees.isEmpty()){
          throw  new ResourceNotFoundException("No employees found");
      }

      List<EmployeeDto> employeeDtoList = employees.stream().map(emp->modelMapper.map(emp,EmployeeDto.class)).toList();

      List<EmployeeDto> response = new ArrayList<>();

        //todo : return employeeDetails  With Address
        for(EmployeeDto employee: employeeDtoList ){
                List<AddressDto> addresses = new ArrayList<>();

            try{
                addresses = addressClient.getSingleAddress(employee.getId());
                employee.setAddress(addresses);

            } catch (Exception e){
                log.error("Address Not Found with employee id :"+employee.getId());
            }

           response.add(employee);
        }

        return response;

    }


    //use below microservice function to get Single Address as well as employeeDetails both =  addressByEmployeeID
    @Override
    public EmployeeDto getSinglEmp(Long id) {

        if (id == null) {
            throw new BadRequestException("Please provide employee id");
        }
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + id));

        List<AddressDto> addresses = new ArrayList<>();
        EmployeeDto empDto =  modelMapper.map(employee, EmployeeDto.class);
//        try{

        addresses = addressClient.getSingleAddress(id);
        empDto.setAddress(addresses);
        //connect  address function to get
        //if employee id not found in address table then throw errror - address not found

//        } catch (Exception e){
//        log.error("Address Not Found with employee id :"+employee.getId());
//        }

        //add addreesOfEmp to EmployeeDetails
        return empDto;

    }

    @Override
    public EmployeeDto UpdateEmployee(Long id, EmployeeDto employeeDto) {

        if (id == null) {
            throw new BadRequestException("Please provide Employee Id");
        }

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//                        RuntimeException("Employee not found"));

        // update fields

        existingEmployee.setEmpName(employeeDto.getEmpName());
        existingEmployee.setEmpEmail(employeeDto.getEmpEmail());
        existingEmployee.setEmpCode(employeeDto.getEmpCode());
        existingEmployee.setCompanyName(employeeDto.getCompanyName());

        existingEmployee.setUpdatedAt(LocalDateTime.now());
        Employee savedEntity = employeeRepository.save(existingEmployee);

        return modelMapper.map(savedEntity, EmployeeDto.class);
    }


    //find or getEmployeeById
    public EmployeeDto findByempCodeAndCompanyName(String empCode,String companyName){

          if (empCode == null || companyName == null){
            throw  new BadRequestException("Please provide empCode And CompanyName");
        }

        Employee employeeFind =   employeeRepository.findByEmpCodeAndCompanyName(empCode,companyName);
        if(employeeFind.toString().isEmpty()){
        throw  new ResourceNotFoundException("Employee not found ");
        }
        return modelMapper.map(employeeFind,EmployeeDto.class);
      }



}
