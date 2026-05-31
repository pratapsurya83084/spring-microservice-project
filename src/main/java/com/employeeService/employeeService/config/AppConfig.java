package com.employeeService.employeeService.config;

import com.employeeService.employeeService.model.dto.EmployeeDto;
import com.employeeService.employeeService.model.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
       modelMapper.getConfiguration()
               .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
               .setFieldMatchingEnabled(true);
       return modelMapper;
    }

//    public Employee modelMapper(EmployeeDto employeeDto, Class<Employee> employeeClass) {
//    }
}
