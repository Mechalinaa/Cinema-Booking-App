package com.example.cinema_booking.controllers;

import com.example.cinema_booking.models.Movie;
import com.example.cinema_booking.models.Room;
import com.example.cinema_booking.models.Seat;
import com.example.cinema_booking.models.Showtime;
import com.example.cinema_booking.repositories.MovieRepository;
import com.example.cinema_booking.repositories.RoomRepository;
import com.example.cinema_booking.repositories.SeatRepository;
import com.example.cinema_booking.repositories.ShowtimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ShowtimeViewControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    private Movie movie;
    private Room room;
    private Seat seat1;
    private Seat seat2;
    private Showtime showtime;

    @BeforeEach
    void setUp() {
        showtimeRepository.deleteAll();
        roomRepository.deleteAll();
        seatRepository.deleteAll();
        movieRepository.deleteAll();

        movie = new Movie();
        movie.setTitle("Matrix");
        movie.setDirector("Wachowski");
        movie.setDescription("Sci-fi");
        movie.setAgeRestriction(16);
        movie.setDurationInMinutes(136);
        movie.setGenre("Sci-Fi");
        movieRepository.save(movie);

        seat1 = new Seat();
        seat1.setRowNum(1);
        seat1.setSeatNum(1);

        seat2 = new Seat();
        seat2.setRowNum(1);
        seat2.setSeatNum(2);

        room = new Room();
        room.setName("Sala 1");
        room.setSeats(List.of(seat1, seat2));
        roomRepository.save(room);

        showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setRoom(room);
        showtime.setStartTime(LocalDateTime.now().withNano(0));
        showtimeRepository.save(showtime);
    }

    @Test
    void shows_shouldReturnAllShowtimes() throws Exception {
        mockMvc.perform(get("/showtimes").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("showtimes"))
                .andExpect(model().attributeExists("showtimes"))
                .andExpect(model().attribute("pageTitle", "Seanse"))
                .andExpect(result -> {
                    @SuppressWarnings("unchecked")
                    List<Showtime> showtimes = (List<Showtime>) result.getModelAndView().getModel().get("showtimes");
                    assertThat(showtimes).hasSize(1);
                    assertThat(showtimes.get(0).getId()).isEqualTo(showtime.getId());
                    assertThat(showtimes.get(0).getMovie().getTitle()).isEqualTo("Matrix");
                });
    }

    @Test
    void showDetails_shouldReturnShowtimeWithSeatsGroupedByRow() throws Exception {
        mockMvc.perform(get("/showtimes/{id}", showtime.getId()).accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("showDetails"))
                .andExpect(model().attributeExists("show"))
                .andExpect(model().attributeExists("seatsByRow"))
                .andExpect(model().attribute("pageTitle", "Seans"))
                .andExpect(result -> {
                    Showtime s = (Showtime) result.getModelAndView().getModel().get("show");
                    assertThat(s.getId()).isEqualTo(showtime.getId());

                    @SuppressWarnings("unchecked")
                    Map<Integer, List<Seat>> seatsByRow =
                            (Map<Integer, List<Seat>>) result.getModelAndView().getModel().get("seatsByRow");
                    assertThat(seatsByRow).containsKey(1);

                    assertThat(seatsByRow.get(1))
                            .extracting(Seat::getId)
                            .containsExactly(seat1.getId(), seat2.getId());
                });
    }

}
