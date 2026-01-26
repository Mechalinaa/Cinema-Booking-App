package com.example.cinema_booking.controllers;

import com.example.cinema_booking.models.Movie;
import com.example.cinema_booking.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Profil H2
class MovieViewControllerITest {

    @Autowired
    private MockMvc mockMvc;

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
        movie1.setDescription("Film sci-fi");
        movie1.setAgeRestriction(16);
        movie1.setDurationInMinutes(136);
        movie1.setGenre("Sci-Fi");
        movieRepository.save(movie1);

        movie2 = new Movie();
        movie2.setTitle("Inception");
        movie2.setDirector("Nolan");
        movie2.setDescription("Film akcji/sci-fi");
        movie2.setAgeRestriction(12);
        movie2.setDurationInMinutes(148);
        movie2.setGenre("Sci-Fi/Action");
        movieRepository.save(movie2);
    }

    @Test
    void moviesPage_shouldReturnAllMovies() throws Exception {
        mockMvc.perform(get("/movies")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("movies"))
                .andExpect(model().attributeExists("movies"))
                .andExpect(model().attributeExists("pageTitle"))
                .andExpect(result -> {
                    List<Movie> movies = (List<Movie>) result.getModelAndView().getModel().get("movies");

                    assertThat(movies).hasSize(2);

                    assertThat(movies)
                            .extracting(Movie::getTitle)
                            .containsExactlyInAnyOrder("Matrix", "Inception");

                    assertThat(result.getModelAndView().getModel().get("pageTitle"))
                            .isEqualTo("Filmy");
                });
    }
}
