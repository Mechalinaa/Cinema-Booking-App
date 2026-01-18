package com.example.cinema_booking.services;

import com.example.cinema_booking.models.Movie;
import com.example.cinema_booking.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public Movie findById(UUID id) {
        return movieRepository.findByIdOrElseThrow(id);
    }

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Transactional
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Transactional
    public void deleteById(UUID id) {
        movieRepository.deleteById(id);
    }
}
