package com.flight.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdminServices1Application {

	public static void main(String[] args) {
		SpringApplication.run(AdminServices1Application.class, args);
	}

}
