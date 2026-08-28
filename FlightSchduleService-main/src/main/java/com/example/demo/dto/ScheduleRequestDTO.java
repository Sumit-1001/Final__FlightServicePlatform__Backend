package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ScheduleRequestDTO {
	private int flightId;
	private LocalDate arrivalDate;
	private LocalDate departureDate;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private int availableSeats; 
	private int price;
}
