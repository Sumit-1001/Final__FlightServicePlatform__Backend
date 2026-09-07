package com.flight.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.flight.demo.entity.Flight;
import com.flight.demo.enums.DestinationLocation;
import com.flight.demo.enums.SourceLocation;


public interface IFlightService {

	
	public String addFlight(Flight flight);
	
	public Flight viewFlight(int flightId);
	
	public List<Flight> viewAllFlights();
	
	public String updateSource(int flightId, SourceLocation source);
	
	public String updateDestination(int flightId, DestinationLocation destination);
	
	public String deleteFlight(int flightId);
	
	public String updateFlight(int flightId,Flight flight);
	
	List<Flight> getFlightsBySource(SourceLocation source);

	List<Flight> getFlightsByDestination(DestinationLocation destination);

	List<Flight> getFlightsBySourceAndDestination(
	        SourceLocation source,
	        DestinationLocation destination);
}
