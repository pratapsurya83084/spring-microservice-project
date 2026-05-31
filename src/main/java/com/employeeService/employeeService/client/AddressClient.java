package com.employeeService.employeeService.client;

//import com.employeeService.employeeService.model.dto.AddressDto;
import com.employeeService.employeeService.model.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "ADDRESS")
public interface AddressClient {

    @GetMapping("/api/address/get-singleAddress")
    List<AddressDto> getSingleAddress(@RequestParam Long id);
}