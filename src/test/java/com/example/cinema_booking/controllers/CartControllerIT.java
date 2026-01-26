package com.example.cinema_booking.controllers;

import com.example.cinema_booking.models.*;
import com.example.cinema_booking.repositories.*;
import com.example.cinema_booking.services.ReservationService;
import com.example.cinema_booking.services.SeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CartControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SeatService seatService;

    @Autowired
    private ReservationService reservationService;

    private Showtime showtime;
    private Seat seat1;
    private Seat seat2;

    @BeforeEach
    void setup() {
        reservationRepository.deleteAll();
        showtimeRepository.deleteAll();
        roomRepository.deleteAll();
        seatRepository.deleteAll();
        movieRepository.deleteAll();

        Movie movie = new Movie();
        movie.setTitle("Matrix");
        movie.setDirector("Wachowski");
        movie.setDescription("Film sci-fi");
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

        Room room = new Room();
        room.setName("Sala 1");
        room.setSeats(List.of(seat1, seat2)); // Hibernate zapisze seaty razem z Room
        roomRepository.save(room);

        showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setRoom(room);
        showtime.setStartTime(LocalDateTime.now());
        showtimeRepository.save(showtime);
    }

    @Test
    @WithMockUser
    void addToCart_shouldAddSeatsAndRedirect() throws Exception {
        mockMvc.perform(post("/cart/add")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("seatIds", seat1.getId().toString(), seat2.getId().toString())
                        .param("showtimeId", showtime.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart/view"));
    }

    @Test
    @WithMockUser
    void viewCart_shouldShowCartPage() throws Exception {
        mockMvc.perform(get("/cart/view"))
                .andExpect(status().isOk())
                .andExpect(view().name("cart"))
                .andExpect(model().attributeExists("cart"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attribute("pageTitle", "Koszyk"));
    }

    @Test
    @WithMockUser
    void updateTicket_shouldChangeTypeAndPrice() throws Exception {
        mockMvc.perform(post("/cart/add")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("seatIds", seat1.getId().toString())
                        .param("showtimeId", showtime.getId().toString()))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(post("/cart/updateTicket")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("seatId", seat1.getId().toString())
                        .param("ticketType", "ULGOWY"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart/view"));
    }

    @Test
    @WithMockUser
    void removeFromCart_shouldRemoveSeat() throws Exception {
        mockMvc.perform(post("/cart/add")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("seatIds", seat1.getId().toString())
                        .param("showtimeId", showtime.getId().toString()))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(post("/cart/remove")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("seatId", seat1.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart/view"));
    }

    @Test
    @WithMockUser
    @Transactional
    void confirmReservation_shouldPersistReservationAndClearCart() throws Exception {
        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(post("/cart/add")
                        .with(csrf())
                        .session(session) // <- ważne, używamy tej samej sesji
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("seatIds", seat1.getId().toString(), seat2.getId().toString())
                        .param("showtimeId", showtime.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart/view"));

        mockMvc.perform(post("/cart/confirm")
                        .with(csrf())
                        .session(session)) // <- ta sama sesja
                .andExpect(status().is3xxRedirection())
                .andExpect(result -> {
                    String location = result.getResponse().getRedirectedUrl();
                    assertThat(location).startsWith("/reservation/");

                    UUID reservationId = UUID.fromString(location.replace("/reservation/", ""));
                    var reservation = reservationRepository.findById(reservationId).orElseThrow();

                    assertThat(reservation.getSeats()).hasSize(2);
                    assertThat(reservation.getMovieName()).isEqualTo("Matrix");
                    assertThat(reservation.getTotalPrice()).isEqualByComparingTo(BigDecimal.valueOf(50));
                });
    }
}
