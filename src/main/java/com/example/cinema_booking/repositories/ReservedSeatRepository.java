package com.example.cinema_booking.repositories;

import com.example.cinema_booking.models.ReservedSeat;
import com.example.cinema_booking.models.Seat;
import com.example.cinema_booking.models.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, UUID> {
    boolean existsByShowtimeAndSeat(Showtime showtime, Seat seat);

    List<ReservedSeat> findByShowtime(Showtime showtime);
}