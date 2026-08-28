package com.flight.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.demo.entity.Flight;
import com.flight.demo.enums.DestinationLocation;
import com.flight.demo.enums.SourceLocation;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer>{

	boolean existsByFlightNumber(String flightNumber);
	
	Flight findByFlightNumber(String flightNumber);
	
	List<Flight> findAllBySource(SourceLocation source);
	
	List<Flight> findAllByDestination(DestinationLocation destination);
	
	List<Flight> findBySourceAndDestination(SourceLocation source,DestinationLocation destination);
}
