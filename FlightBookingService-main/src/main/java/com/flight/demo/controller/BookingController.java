package com.flight.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.demo.dto.BookingRequestDTO;
import com.flight.demo.dto.BookingResponseDTO;
import com.flight.demo.entity.Booking;
import com.flight.demo.service.IBookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(
            @RequestBody BookingRequestDTO request) {

        return new ResponseEntity<>(
                bookingService.createBooking(request),
                HttpStatus.CREATED);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingById(
            @PathVariable Integer bookingId) {

        return ResponseEntity.ok(
                bookingService.getBookingById(bookingId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(
            @PathVariable Integer userId) {

        return ResponseEntity.ok(
                bookingService.getBookingsByUser(userId));
    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<Booking>> getBookingsBySchedule(
            @PathVariable Integer scheduleId) {

        return ResponseEntity.ok(
                bookingService.getBookingsBySchedule(scheduleId));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> cancelBooking(
            @PathVariable Integer bookingId) {

        return ResponseEntity.ok(
                bookingService.cancelBooking(bookingId));
    }
}
