package com.example.cinema_booking.repositories;

import com.example.cinema_booking.models.Seat;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID> {
    default Seat findByIdOrElseThrow(UUID id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
