package com.flight.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.demo.client.ScheduleFeignClient;
import com.flight.demo.client.UserFeignClient;
import com.flight.demo.dto.BookingRequestDTO;
import com.flight.demo.dto.BookingResponseDTO;
import com.flight.demo.dto.ScheduleDTO;
import com.flight.demo.dto.UserDTO;
import com.flight.demo.entity.Booking;
import com.flight.demo.enums.BookingStatus;
import com.flight.demo.exceptions.BookingNotFoundException;
import com.flight.demo.exceptions.ScheduleNotFoundException;
import com.flight.demo.exceptions.SeatNotAvailableException;
import com.flight.demo.exceptions.UserNotFoundException;
import com.flight.demo.repository.BookingRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements IBookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private ScheduleFeignClient scheduleFeignClient;
	
	@Transactional
	@Override
	public BookingResponseDTO createBooking(BookingRequestDTO request){
		
		
		UserDTO user=userFeignClient.getUserById(request.getUserId());
		if(user == null) {
			throw new UserNotFoundException("User not found with id "+ request.getUserId());
		}
		ScheduleDTO schedule=scheduleFeignClient.getScheduleById(request.getScheduleId());
		if(schedule == null) {
			throw new ScheduleNotFoundException("Schedule not found with id "+ request.getScheduleId());
		}
		if(schedule.getAvailableSeats()<request.getNumberOfSeats()) {
			throw new SeatNotAvailableException("Seats not available");
		}
		
		double totalFare= schedule.getPrice()*request.getNumberOfSeats();
		
		Booking booking =new Booking();
		
		booking.setUserId(request.getUserId());
		booking.setScheduleId(request.getScheduleId());
		booking.setSeatsBooked(request.getNumberOfSeats());
		booking.setTotalFare(totalFare);
		booking.setBookingStatus(BookingStatus.CONFIRMED);
		booking.setBookingDate(LocalDate.now());
		
		bookingRepository.save(booking);
		
		scheduleFeignClient.updateAvailableSeats(
		        request.getScheduleId(),
		        request.getNumberOfSeats());
		
		return new BookingResponseDTO(
				booking.getBookingId(),
				user.getUserName(),
				booking.getScheduleId(),
				booking.getSeatsBooked(),
				booking.getTotalFare(),
				booking.getBookingStatus()
				);
	}
	
	@Override
	public BookingResponseDTO getBookingById(Integer bookingId) {
		
		Booking booking=bookingRepository.findById(bookingId)
				.orElseThrow(()->new BookingNotFoundException(
						"Booking not found with booking id: "+bookingId));
		
		UserDTO user=userFeignClient.getUserById(booking.getUserId());
		
		return new BookingResponseDTO(
				booking.getBookingId(),
				user.getUserName(),
				booking.getScheduleId(),
				booking.getSeatsBooked(),
				booking.getTotalFare(),
				booking.getBookingStatus());
	}

	@Override
	public List<Booking> getBookingsByUser(Integer userId) {

	    System.out.println("METHOD CALLED");

	    List<Booking> bookings = bookingRepository.findByUserId(userId);

	  

	    return bookings;
	}
	
	@Override
	public List<Booking> getBookingsBySchedule(Integer scheduleId) {
		// TODO Auto-generated method stub
		return bookingRepository.findByScheduleId(scheduleId);
	}

	@Transactional
	@Override
	public String cancelBooking(Integer bookingId) {

		Booking booking = bookingRepository.findById(bookingId)
					.orElseThrow(() ->new BookingNotFoundException("Booking not found with id "+ bookingId));

		scheduleFeignClient.addSeats(booking.getScheduleId(),booking.getSeatsBooked());
	
		bookingRepository.delete(booking);
		return "Booking cancelled successfully";

	}
}
