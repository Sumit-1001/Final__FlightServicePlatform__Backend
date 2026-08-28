package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FlightSchduleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSchduleServiceApplication.class, args);
	}

}
