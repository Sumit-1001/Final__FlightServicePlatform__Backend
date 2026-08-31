package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="flight_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSchedule {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleId;

	private int flightId;  
	private LocalDate departureDate;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private int availableSeats; 
	private int totalCapacity;
	//flight 
	private String source;
	private String destination;
	private double price;
	public FlightSchedule(int flightId, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime,
			int availableSeats, int totalCapacity, String source, String destination,double price) {
		super();
		this.flightId = flightId;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.availableSeats = availableSeats;
		this.totalCapacity = totalCapacity;
		this.source = source;
		this.destination = destination;
		this.price=price;
	}
	}
	
	
