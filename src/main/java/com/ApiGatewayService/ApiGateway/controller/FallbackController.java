package com.ApiGatewayService.ApiGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {


    @GetMapping("/EmployeeServiceFallback")
    public Mono<String> EmployeeServiceFallback(){
      return   Mono.just("Employee  Service is Down, Please Try Again Later.");
    }

    @GetMapping("/AddressServiceFallback")
    public Mono<String> AddressServiceFallback(){
       return Mono.just("Address Service is Down,Please Try Again Later.");
    }

}
