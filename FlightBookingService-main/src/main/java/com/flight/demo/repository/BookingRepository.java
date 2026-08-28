package com.flight.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.demo.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>{

	public List<Booking>findByUserId(Integer userId);
	
	public List<Booking>findByScheduleId(Integer scheduleId);
}
