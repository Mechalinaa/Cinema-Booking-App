package com.example.cinema_booking.repositories;

import com.example.cinema_booking.models.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;
    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp() {
        movieRepository.deleteAll();
        movie1 = new Movie();
        movie1.setTitle("Matrix");
        movie1.setDirector("Wachowski");
        movie1.setDescription("Sci-fi film");
        movie1.setGenre("Sci-Fi");
        movie1.setAgeRestriction(16);
        movie1.setDurationInMinutes(136);
        movie2 = new Movie();
        movie2.setTitle("Inception");
        movie2.setDirector("Nolan");
        movie2.setDescription("Action/Sci-fi");
        movie2.setGenre("Sci-Fi/Action");
        movie2.setAgeRestriction(12);
        movie2.setDurationInMinutes(148);
        movieRepository.saveAll(List.of(movie1, movie2));
    }

    @Test
    void shouldSaveAndFindMovieById() {
        Movie found = movieRepository.findById(movie1.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("Matrix");
    }

    @Test
    void findByIdOrElseThrow_shouldReturnMovie() {
        Movie found = movieRepository.findByIdOrElseThrow(movie2.getId());
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("Inception");
    }

    @Test
    void findByIdOrElseThrow_shouldThrowExceptionIfNotFound() {
        UUID randomId = UUID.randomUUID();
        assertThatThrownBy(() -> movieRepository.findByIdOrElseThrow(randomId)).
                isInstanceOfAny(jakarta.persistence.EntityNotFoundException.class,
                        org.springframework.orm.jpa.JpaObjectRetrievalFailureException.class);
    }

    @Test
    void shouldFindAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        assertThat(movies)
                .hasSize(2).extracting(Movie::getTitle)
                .containsExactlyInAnyOrder("Matrix", "Inception");
    }

    @Test
    void shouldDeleteMovie() {
        movieRepository.delete(movie1);
        List<Movie> movies = movieRepository.findAll();
        assertThat(movies)
                .hasSize(1)
                .extracting(Movie::getTitle)
                .containsExactly("Inception");
    }
}