package com.example.cinema_booking.repositories;

import com.example.cinema_booking.models.Movie;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    default Movie findByIdOrElseThrow(UUID id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
