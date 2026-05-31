package com.Authmicroservice.Authmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthmicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthmicroserviceApplication.class, args);
        System.out.println("Auth Service Is Running...");
	}

}
