package com.example.cinema_booking.controllers;

import com.example.cinema_booking.models.Reservation;
import com.example.cinema_booking.repositories.ReservationRepository;
import com.example.cinema_booking.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ReservationViewControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        reservationRepository.deleteAll();

        reservation = new Reservation();
        reservation.setMovieName("Matrix");
        reservation.setSeats(List.of("A1", "A2"));
        reservation.setStartTime(LocalDateTime.now().withNano(0));
        reservation.setTotalPrice(BigDecimal.valueOf(50));

        reservationRepository.save(reservation);
    }

    @Test
    void reservationSummary_shouldReturnReservationDetails() throws Exception {
        mockMvc.perform(get("/reservation/{id}", reservation.getId())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("reservation"))
                .andExpect(model().attributeExists("reservation"))
                .andExpect(model().attribute("pageTitle", "Rezerwacja"))
                .andExpect(result -> {
                    Reservation res = (Reservation) result.getModelAndView().getModel().get("reservation");

                    assertThat(res.getId()).isEqualTo(reservation.getId());
                    assertThat(res.getMovieName()).isEqualTo(reservation.getMovieName());
                    assertThat(res.getSeats()).containsExactlyInAnyOrderElementsOf(reservation.getSeats());

                    // Porównanie LocalDateTime z ignorowaniem nanosekund
                    assertThat(res.getStartTime().withNano(0))
                            .isEqualTo(reservation.getStartTime().withNano(0));

                    assertThat(res.getTotalPrice()).isEqualByComparingTo(reservation.getTotalPrice());
                });
    }
}
