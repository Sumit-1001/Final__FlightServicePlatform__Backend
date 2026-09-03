package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FlightSchedule;

@Repository
public interface FlightSchduleRepository extends JpaRepository<FlightSchedule, Integer> {

	List<FlightSchedule> findByFlightId(int flightId);
	
	
	List<FlightSchedule> findBySource(String source);
    
	List<FlightSchedule> findByDepartureDate(LocalDate departureDate);
	
	List<FlightSchedule> findByAvailableSeatsGreaterThan(int seats);
	
	List<FlightSchedule> findByDestination(String destination);
	
	List<FlightSchedule> findByDepartureDateAndDestination(LocalDate departureDate,String destination);
	
	boolean existsByFlightId(int flightId);
	
	boolean existsByFlightIdAndDepartureTime(
	        Integer flightId,
	        LocalTime DepartureTime);
 
}
