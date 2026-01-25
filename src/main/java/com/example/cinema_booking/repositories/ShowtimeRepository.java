package com.example.cinema_booking.repositories;

import com.example.cinema_booking.models.Room;
import com.example.cinema_booking.models.Showtime;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, UUID> {
    default Showtime findByIdOrElseThrow(UUID id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }
    List<Showtime> findByRoom(Room room);
    List<Showtime> findByStartTimeBetween(
            LocalDateTime start,
            LocalDateTime end
    );
}
