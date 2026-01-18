package com.example.cinema_booking.repositories;

import com.example.cinema_booking.models.Reservation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    default Reservation findByIdOrElseThrow(UUID id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
