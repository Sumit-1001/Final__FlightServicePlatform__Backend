package com.edu.test.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ScheduleDTO {

	
	private int scheduleId;

	private int flightId;  
	private LocalDate departureDate;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private String source;
	private String destination;
	private int availableSeats;
	private double price;
}
