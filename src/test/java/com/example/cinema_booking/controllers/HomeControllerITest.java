package com.example.cinema_booking.controllers;

import com.example.cinema_booking.models.Movie;
import com.example.cinema_booking.models.Room;
import com.example.cinema_booking.models.Showtime;
import com.example.cinema_booking.repositories.MovieRepository;
import com.example.cinema_booking.repositories.RoomRepository;
import com.example.cinema_booking.repositories.ShowtimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class HomeControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    private Movie movie;
    private Room room;
    private Showtime showtime;

    @BeforeEach
    void setUp() {
        showtimeRepository.deleteAll();
        movieRepository.deleteAll();
        roomRepository.deleteAll();

        movie = new Movie();
        movie.setTitle("Matrix");
        movie.setDirector("Wachowski");
        movie.setDescription("Sci-fi");
        movie.setAgeRestriction(16);
        movie.setDurationInMinutes(136);
        movie.setGenre("Sci-Fi");
        movieRepository.save(movie);

        room = new Room();
        room.setName("Sala 1");
        roomRepository.save(room);

        showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setRoom(room);
        showtime.setStartTime(LocalDateTime.now().withHour(20).withMinute(0).withSecond(0).withNano(0));
        showtimeRepository.save(showtime);
    }

    @Test
    void home_shouldReturnShowtimesGroupedByMovie() throws Exception {
        LocalDate today = LocalDate.now();

        mockMvc.perform(get("/").param("date", today.toString()).accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("selectedDate"))
                .andExpect(model().attributeExists("repertoire"))
                .andExpect(result -> {
                    var repertoire = (Map<Movie, List<Showtime>>) result.getModelAndView().getModel().get("repertoire");

                    boolean hasMovie = repertoire.keySet().stream()
                            .anyMatch(m -> m.getId().equals(movie.getId()));
                    assertThat(hasMovie).isTrue();

                    List<Showtime> showtimesForMovie = repertoire.entrySet().stream()
                            .filter(e -> e.getKey().getId().equals(movie.getId()))
                            .flatMap(e -> e.getValue().stream())
                            .toList();

                    assertThat(showtimesForMovie).hasSize(1);

                    assertThat(showtimesForMovie.get(0).getStartTime())
                            .isEqualToIgnoringNanos(showtime.getStartTime());
                });
    }

    @Test
    void home_shouldDefaultToTodayIfDateNotProvided() throws Exception {
        mockMvc.perform(get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("selectedDate"))
                .andExpect(model().attributeExists("repertoire"))
                .andExpect(result -> {
                    var selectedDate = (LocalDate) result.getModelAndView().getModel().get("selectedDate");
                    assertThat(selectedDate).isEqualTo(LocalDate.now());
                });
    }
}
