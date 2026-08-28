package com.flight.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.demo.entity.Flight;
import com.flight.demo.enums.DestinationLocation;
import com.flight.demo.enums.SourceLocation;
import com.flight.demo.exceptions.FlightAlreadyExisitsException;
import com.flight.demo.exceptions.FlightNotFoundException;
import com.flight.demo.repository.FlightRepository;

import jakarta.transaction.Transactional;

@Service
public class FlightServiceImpl implements IFlightService{
	
	@Autowired
	private FlightRepository repository;
	
	@Transactional
	@Override
	public String addFlight(Flight flight) {
		
		if(repository.existsByFlightNumber(flight.getFlightNumber())) {
			throw new FlightAlreadyExisitsException(
					"Flight Already exists with Flight number "+flight.getFlightNumber());
		}
		
		Flight savedFlight=repository.save(flight);
		return "Flight Saved Successfully "+savedFlight.getFlightId();
	}

	@Override
	public Flight viewFlight(int flightId) {
		
		return repository.findById(flightId)
				.orElseThrow(()->new FlightNotFoundException("No such Flight Exist with flight id: " + flightId));
	}

	@Override
	public List<Flight> viewAllFlights() {
		
		return repository.findAll();
	}

	@Transactional
	@Override
	public String updateSource(int flightId, SourceLocation source) {
		
		Flight flight=repository.findById(flightId)
				.orElseThrow(()->new FlightNotFoundException("No such Flight Exist with flight id: "+flightId));
		
		flight.setSource(source);
		repository.save(flight);
		return flightId+" Source Updated successfully!!!";
	}

	@Transactional
	@Override
	public String updateDestination(int flightId, DestinationLocation destination) {

		Flight flight=repository.findById(flightId)
				.orElseThrow(()->new FlightNotFoundException("No such Flight Exist with flight id: "+flightId));
		
		flight.setDestination(destination);
		repository.save(flight);
		return flightId+" Destination Updated successfully!!!";
	}

	@Transactional
	@Override
	public String deleteFlight(int flightId) {
		
		Flight flight= repository.findById(flightId)
				.orElseThrow(()->new FlightNotFoundException("No such Flight Exist with flight id: "+flightId));
		
		repository.delete(flight);
		return flightId+" Flight Deleted Successfully!!!";
	}

	@Transactional
	@Override
	public String updateFlight(int flightId, Flight flight) {
		// here i used "int" dataType
		Flight existingFlight=repository.findById(flightId)
				.orElseThrow(()-> new FlightNotFoundException("No such Flight Exist with flight id: "+flightId));
		
		if(repository.existsByFlightNumber(flight.getFlightNumber())
		        && existingFlight.getFlightNumber().equals(flight.getFlightNumber())) {
		    throw new FlightAlreadyExisitsException("");
		}
		
		existingFlight.setFlightNumber(flight.getFlightNumber());
		existingFlight.setAirline(flight.getAirline());
		existingFlight.setSource(flight.getSource());
		existingFlight.setDestination(flight.getDestination());
		existingFlight.setTotalSeats( flight.getTotalSeats());
		repository.save(existingFlight);
		return "Flight updated successfully";
	}

	@Override
	public List<Flight> getFlightsBySource(SourceLocation source) {
	    return repository.findAllBySource(source);
	}

	@Override
	public List<Flight> getFlightsByDestination(DestinationLocation destination) {
	    return repository.findAllByDestination(destination);
	}

	@Override
	public List<Flight> getFlightsBySourceAndDestination(
	        SourceLocation source,
	        DestinationLocation destination) {

	    return repository.findBySourceAndDestination(source, destination);
	}

}
