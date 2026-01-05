package com.example.cinema_booking.repository;

import com.example.cinema_booking.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
