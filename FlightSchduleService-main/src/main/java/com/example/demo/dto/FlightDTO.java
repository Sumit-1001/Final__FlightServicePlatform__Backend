package com.example.demo.dto;

import lombok.Data;

@Data
public class FlightDTO {
	
	
	private int flightId;

	private String source;
	
	private String destination;
	
	private int totalSeats;

}
