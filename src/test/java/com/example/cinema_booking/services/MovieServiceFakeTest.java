package com.example.cinema_booking.services;

import com.example.cinema_booking.models.Movie;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceFakeTest {

    private MovieService movieService;
    private InMemoryMovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        movieRepository = new InMemoryMovieRepository();
        movieService = new MovieService(movieRepository);
    }

    @Test
    void shouldSaveAndFindMovieById() {
        Movie movie = new Movie();
        movie.setTitle("Inception");

        Movie saved = movieService.save(movie);
        Movie found = movieService.findById(saved.getId());

        assertNotNull(found);
        assertEquals("Inception", found.getTitle());
    }

    @Test
    void shouldReturnAllMovies() {
        movieService.save(new Movie());
        movieService.save(new Movie());

        var movies = movieService.findAll();

        assertEquals(2, movies.size());
    }

    @Test
    void shouldDeleteMovie() {
        Movie movie = movieService.save(new Movie());
        UUID id = movie.getId();

        movieService.deleteById(id);

        assertThrows(EntityNotFoundException.class,
                () -> movieService.findById(id));
    }
}
