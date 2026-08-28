package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.FlightSchedule;

@Service
public interface FlightScheduleService {
	
	public FlightSchedule addSchedule(FlightSchedule schedule);
	
	public FlightSchedule getScheduleById(int id);
	
	public List<FlightSchedule> getAllSchedules();
	
	public FlightSchedule updateSchedule(int scheduleId,FlightSchedule schedule);
	
	public String deleteSchedule(int scheduleId);
	
	public int checkAvailableSeats(int scheduleId);
	
    public String updateAvailableSeats(int scheduleId,int seatsBooked);
	
	public String addSeatsBack(int scheduleId,  int seatsCancelled);
	
	
	//JPA Queries Implemented
	public List<FlightSchedule> getSchedulesByFlightId(int flightId);
	
	public List<FlightSchedule> getSchedulesByDate(LocalDate date);
	
	public List<FlightSchedule> getSchedulesByDestination(String destination);
	
	public List<FlightSchedule> getFlightsByAvailableSeats(int seats);
	
	

}
