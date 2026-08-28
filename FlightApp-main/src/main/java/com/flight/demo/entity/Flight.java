package com.flight.demo.entity;

import com.flight.demo.enums.DestinationLocation;
import com.flight.demo.enums.RequestAirline;
import com.flight.demo.enums.SourceLocation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int flightId;
	
	@Column(unique=true ,nullable=false)
	private String flightNumber;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private RequestAirline airline; 
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private SourceLocation source;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private DestinationLocation destination;
	
	@Min(1)
	@NotNull
	@Positive
	private  int totalSeats;

	public Flight(String flightNumber,RequestAirline airline,SourceLocation source,
			DestinationLocation destination, int totalSeats) {
		super();
		this.flightNumber = flightNumber;
		this.airline = airline;
		this.source = source;
		this.destination = destination;
		this.totalSeats = totalSeats;
	}
	
	
}
