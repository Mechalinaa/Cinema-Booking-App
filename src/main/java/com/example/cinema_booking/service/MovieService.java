package com.example.cinema_booking.service;

import com.example.cinema_booking.model.Movie;
import com.example.cinema_booking.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public Movie save(Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public Movie findById(Long id){
        return movieRepository.findById(id).orElse(null);
    }
}
