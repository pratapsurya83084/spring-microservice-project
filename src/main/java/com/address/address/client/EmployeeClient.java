package com.address.address.client;

import com.address.address.model.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "employeeClient",url = "${employee.service.url}")
public interface EmployeeClient {

    @GetMapping("getsingle-emp")
    EmployeeDto GetSingleEmp(@RequestParam Long id);

}
