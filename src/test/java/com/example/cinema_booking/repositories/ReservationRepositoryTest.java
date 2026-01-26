package com.example.cinema_booking.repositories;

import com.example.cinema_booking.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    private Reservation reservation1;
    private Reservation reservation2;

    @BeforeEach
    void setUp() {
        reservationRepository.deleteAll();

        reservation1 = new Reservation();
        reservation1.setMovieName("Matrix");
        reservation1.setSeats(List.of("A1", "A2"));
        reservation1.setStartTime(LocalDateTime.now().withNano(0));
        reservation1.setTotalPrice(BigDecimal.valueOf(50));

        reservation2 = new Reservation();
        reservation2.setMovieName("Inception");
        reservation2.setSeats(List.of("B1", "B2"));
        reservation2.setStartTime(LocalDateTime.now().plusDays(1).withNano(0));
        reservation2.setTotalPrice(BigDecimal.valueOf(60));

        reservationRepository.saveAll(List.of(reservation1, reservation2));
    }

    @Test
    void shouldSaveAndFindReservationById() {
        Reservation found = reservationRepository.findById(reservation1.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getMovieName()).isEqualTo("Matrix");
        assertThat(found.getSeats()).containsExactlyInAnyOrder("A1", "A2");
        assertThat(found.getTotalPrice()).isEqualByComparingTo(BigDecimal.valueOf(50));
    }

    @Test
    void findByIdOrElseThrow_shouldReturnReservation() {
        Reservation found = reservationRepository.findByIdOrElseThrow(reservation2.getId());
        assertThat(found).isNotNull();
        assertThat(found.getMovieName()).isEqualTo("Inception");
    }

    @Test
    void findByIdOrElseThrow_shouldThrowExceptionIfNotFound() {
        UUID randomId = UUID.randomUUID();
        assertThatThrownBy(() -> reservationRepository.findByIdOrElseThrow(randomId))
                .isInstanceOfAny(jakarta.persistence.EntityNotFoundException.class,
                org.springframework.orm.jpa.JpaObjectRetrievalFailureException.class);
    }

    @Test
    void shouldFindAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        assertThat(reservations)
                .hasSize(2)
                .extracting(Reservation::getMovieName)
                .containsExactlyInAnyOrder("Matrix", "Inception");
    }

    @Test
    void shouldDeleteReservation() {
        reservationRepository.delete(reservation1);
        List<Reservation> reservations = reservationRepository.findAll();
        assertThat(reservations)
                .hasSize(1)
                .extracting(Reservation::getMovieName)
                .containsExactly("Inception");
    }
}
