package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.Admin1FeignClient;
import com.example.demo.dto.FlightDTO;
import com.example.demo.dto.ScheduleRequestDTO;
import com.example.demo.entity.FlightSchedule;
import com.example.demo.exception.FlightNotFoundException;
import com.example.demo.exception.ScheduleAlreadyExistException;
import com.example.demo.exception.ScheduleNotFoundException;
import com.example.demo.exception.SeatNotAvailableException;
import com.example.demo.repository.FlightSchduleRepository;

import jakarta.transaction.Transactional;



@Service
public class FlightScheduleServiceImpl implements IFlightScheduleService {
 @Autowired 
    FlightSchduleRepository repo;
    
    @Autowired
    Admin1FeignClient feignClient;
	
    @Override
    public FlightSchedule addSchedule(ScheduleRequestDTO schedule) {

        FlightDTO flight = feignClient.getFlightById(schedule.getFlightId());

        if (flight == null) {
            throw new FlightNotFoundException("Flight not found");
        }
        
        if(repo.existsByFlightId(schedule.getFlightId()))
        {
        	throw new ScheduleAlreadyExistException("Schedule already exist for this flight !!");
        }

        FlightSchedule newFlightSchedule = new FlightSchedule();

        
        newFlightSchedule.setFlightId(schedule.getFlightId());
        newFlightSchedule.setTotalCapacity(flight.getTotalSeats());
        newFlightSchedule.setAvailableSeats(flight.getTotalSeats());
        newFlightSchedule.setSource(flight.getSource());
        newFlightSchedule.setDestination(flight.getDestination());

        
        newFlightSchedule.setDepartureDate(schedule.getDepartureDate());
        newFlightSchedule.setDepartureTime(schedule.getDepartureTime());
        newFlightSchedule.setArrivalTime(schedule.getArrivalTime());
        newFlightSchedule.setPrice(schedule.getPrice());

        return repo.save(newFlightSchedule);
    }

	@Override
	public FlightSchedule getScheduleById(int scheduleId) {
		return repo.findById(scheduleId).orElseThrow(() ->
		new ScheduleNotFoundException(
		"Schedule not found with id : " + scheduleId));
	}

	@Override
	public List<FlightSchedule> getAllSchedules() {
	   return repo.findAll();
	}

	@Override
	public FlightSchedule updateSchedule(int scheduleId, FlightSchedule schedule) {
		FlightSchedule newSchedule = repo.findById(scheduleId).orElseThrow(() ->
		new FlightNotFoundException(
		"Schedule not found"));
		
		FlightDTO flight = feignClient.getFlightById(schedule.getFlightId());

				if (flight == null) {
				throw new ScheduleNotFoundException("Flight not found");

				}
						
		newSchedule.setFlightId(schedule.getFlightId());		
		newSchedule.setDepartureDate(schedule.getDepartureDate());
		newSchedule.setArrivalTime(schedule.getArrivalTime());
		newSchedule.setDepartureTime(schedule.getDepartureTime());
		
		return repo.save(newSchedule);
	}

	@Override
	public String deleteSchedule(int scheduleId) {
		FlightSchedule schedule = repo.findById(scheduleId).orElseThrow(() ->
		new ScheduleNotFoundException(
		"Schedule not found with id : " + scheduleId));
                repo.delete(schedule);
				return "Schedule Deleted Successfully";	}

	@Override
	public int checkAvailableSeats(int scheduleId) {
		FlightSchedule schedule = repo.findById(scheduleId).orElseThrow(() ->
		new ScheduleNotFoundException(
		"Schedule not found with id : " + scheduleId));
		
		if(schedule.getAvailableSeats() <= 0) {    // For Booking logic we will do here if(schedule.getAvailableSeats()< requestedSeats)
			throw new SeatNotAvailableException(
			"Seats are not available for this flight");
			}
		return schedule.getAvailableSeats();
				
	}
	
	
	@Override
	public String updateAvailableSeats(int scheduleId,int seatsBooked) {
		 FlightSchedule schedule = repo.findById(scheduleId).orElseThrow(() ->
		    new ScheduleNotFoundException("Schedule not found"));
	        if(schedule.getAvailableSeats() < seatsBooked) {
	             throw new SeatNotAvailableException("Seats not available");
 
		    }
	         schedule.setAvailableSeats(schedule.getAvailableSeats() - seatsBooked);
	          repo.save(schedule);
	          return "Seats Updated";
	}
	
	@Override
	@Transactional
	public String addSeatsBack(int scheduleId,  int seatsCancelled) {
 
	    FlightSchedule schedule = repo.findById(scheduleId).orElseThrow(() ->
	                    new ScheduleNotFoundException(
	                            "Schedule not found"));
 
	    schedule.setAvailableSeats( schedule.getAvailableSeats()+ seatsCancelled);
        repo.save(schedule);
         return "Seats Added Back Successfully";
	}
 
	
   // JPA Queries 
	@Override
	public List<FlightSchedule> getSchedulesByFlightId(int flightId) {
		return repo.findByFlightId(flightId);
	
	}

	@Override
	public List<FlightSchedule> getSchedulesByDate(LocalDate date) {
		
		return repo.findByDepartureDate(date);
	}

	@Override
	public List<FlightSchedule> getSchedulesByDestination(String destination) {
		return repo.findByDestination(destination);
		
	}

	@Override
	public List<FlightSchedule> getFlightsByAvailableSeats(int seats) {
		 return repo.findByAvailableSeatsGreaterThan(seats);
		
	}

	

	

}
