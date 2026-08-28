package com.flight.demo.service;

import java.util.List;

import com.flight.demo.dto.BookingRequestDTO;
import com.flight.demo.dto.BookingResponseDTO;
import com.flight.demo.entity.Booking;
public interface IBookingService {

    BookingResponseDTO createBooking(BookingRequestDTO request);

    BookingResponseDTO getBookingById(Integer bookingId);

    List<Booking> getBookingsByUser(Integer userId);

    List<Booking> getBookingsBySchedule(Integer scheduleId);

    String cancelBooking(Integer bookingId);
}