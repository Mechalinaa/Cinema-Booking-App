package com.example.cinema_booking.repository;

import com.example.cinema_booking.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
