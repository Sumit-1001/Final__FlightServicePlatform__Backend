package com.flight.demo.dto;

import com.flight.demo.enums.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {

    private Integer bookingId;

    private String userName;

    private Integer scheduleId;

    private Integer numberOfSeats;

    private Double totalFare;

    private BookingStatus bookingStatus;
}
