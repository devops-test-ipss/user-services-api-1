package com.dev.user_services_api_1;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServicesApi1Application {

	public static void main(String[] args) {
		SpringApplication.run(UserServicesApi1Application.class, args);
	}

}
