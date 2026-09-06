package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ScheduleRequestDTO;
import com.example.demo.entity.FlightSchedule;
import com.example.demo.service.IFlightScheduleService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("admin2/schedules")
public class FlightScheduleController {
	
	@Autowired IFlightScheduleService flightScheduleService;
	
	
	// Create Schedule
	
	@PostMapping("/addschedules")
	@Transactional
	public FlightSchedule addSchedule(@RequestBody ScheduleRequestDTO schedule) {
		return flightScheduleService.addSchedule(schedule);
	}
	
	// Get Schedule By Id
	//@GetMapping("/{id}")
//	public FlightSchedule getScheduleById(
//			@PathVariable int id) {
//			return flightScheduleService.getScheduleById(id);
//			} 
	
	// Get All Schedules
	@GetMapping
	public List<FlightSchedule> getAllSchedules() {
	return flightScheduleService.getAllSchedules();
	}
	
	// Update Schedule
	@Transactional
	@PutMapping("/{id}")
	public FlightSchedule updateSchedule(@PathVariable int id,@RequestBody FlightSchedule schedule) {
	return flightScheduleService.updateSchedule(id, schedule);
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteSchedule(@PathVariable int id) {

	return flightScheduleService.deleteSchedule(id);
	}
	
	
	@GetMapping("/seats/{id}")
	public Integer checkAvailableSeats(@PathVariable int id) {

	return flightScheduleService.checkAvailableSeats(id);
	}
	
	
	@PutMapping("/seats/reduce/{id}/{count}")
	public String updateAvailableSeats(@PathVariable Integer id,@PathVariable Integer count) {
 
	    return flightScheduleService.updateAvailableSeats(id, count);
	}

	
	@PutMapping("/seats/add/{id}/{count}")
	public String addSeatsBack( @PathVariable int id, @PathVariable int count) {
 
	    return flightScheduleService .addSeatsBack(id, count);
	}
	 
	
	@GetMapping("/flight/{flightId}")
	public List<FlightSchedule> getSchedulesByFlightId(@PathVariable int flightId) {

		return flightScheduleService.getSchedulesByFlightId(flightId);
	}
	
	

	@GetMapping("/date/{date}")
	public List<FlightSchedule> getByDate(@PathVariable LocalDate date) {
		return flightScheduleService.getSchedulesByDate(date);
	}
	

	@GetMapping("/destination/{destination}")
	public List<FlightSchedule> getByDestination(@PathVariable String destination) {
		return flightScheduleService.getSchedulesByDestination(destination);
	}
	
	
	@GetMapping("/available/{seats}")
	public List<FlightSchedule> getByAvailableSeats(@PathVariable int seats) {

		return flightScheduleService.getFlightsByAvailableSeats(seats);
	}
	
}
