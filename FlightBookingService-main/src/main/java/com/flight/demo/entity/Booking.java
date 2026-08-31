package com.flight.demo.entity;

import java.time.LocalDate;

import com.flight.demo.enums.BookingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    private Integer userId;

    private Integer scheduleId;

    private Integer seatsBooked;


    @Column(name = "total_fare")
    private Double totalFare;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    private LocalDate bookingDate;
    
    
    public Double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(Double totalFare) {
        this.totalFare = totalFare;
    }
}