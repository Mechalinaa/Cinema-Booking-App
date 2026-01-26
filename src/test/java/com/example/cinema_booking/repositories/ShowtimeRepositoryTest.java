package com.example.cinema_booking.repositories;

import com.example.cinema_booking.models.Movie;
import com.example.cinema_booking.models.Room;
import com.example.cinema_booking.models.Showtime;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class ShowtimeRepositoryTest {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MovieRepository movieRepository;

    private Room room;
    private Movie movie;
    private Showtime showtime1;
    private Showtime showtime2;

    @BeforeEach
    void setUp() {
        showtimeRepository.deleteAll();
        roomRepository.deleteAll();
        movieRepository.deleteAll();

        room = new Room();
        room.setName("Room A");
        roomRepository.save(room);

        movie = new Movie();
        movie.setTitle("Movie A");
        movie.setDirector("John Doe");
        movie.setDescription("A thrilling movie");
        movie.setGenre("Action");
        movie.setDurationInMinutes(120);
        movie.setAgeRestriction(13);
        movieRepository.save(movie);

        showtime1 = new Showtime();
        showtime1.setRoom(room);
        showtime1.setMovie(movie);
        showtime1.setStartTime(LocalDateTime.of(2026, 1, 26, 10, 0));

        showtime2 = new Showtime();
        showtime2.setRoom(room);
        showtime2.setMovie(movie);
        showtime2.setStartTime(LocalDateTime.of(2026, 1, 26, 15, 0));

        showtimeRepository.saveAll(List.of(showtime1, showtime2));
    }

    @Test
    void shouldSaveAndFindShowtimeById() {
        Showtime found = showtimeRepository.findByIdOrElseThrow(showtime1.getId());
        assertThat(found).isNotNull();
        assertThat(found.getStartTime()).isEqualTo(showtime1.getStartTime());
    }

    @Test
    void findByIdOrElseThrow_shouldThrowExceptionIfNotFound() {
        UUID randomId = UUID.randomUUID();
        assertThatThrownBy(() -> showtimeRepository.findByIdOrElseThrow(randomId))
                .isInstanceOfAny(EntityNotFoundException.class,
                        org.springframework.orm.jpa.JpaObjectRetrievalFailureException.class);
    }

    @Test
    void shouldFindShowtimesByRoom() {
        List<Showtime> showtimes = showtimeRepository.findByRoom(room);
        assertThat(showtimes).hasSize(2)
                .extracting(Showtime::getStartTime)
                .containsExactlyInAnyOrder(showtime1.getStartTime(), showtime2.getStartTime());
    }

    @Test
    void shouldFindShowtimesBetweenDates() {
        LocalDateTime start = LocalDateTime.of(2026, 1, 26, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 26, 12, 0);

        List<Showtime> showtimes = showtimeRepository.findByStartTimeBetween(start, end);
        assertThat(showtimes).hasSize(1)
                .extracting(Showtime::getStartTime)
                .containsExactly(showtime1.getStartTime());
    }

    @Test
    void shouldDeleteShowtime() {
        showtimeRepository.delete(showtime1);
        List<Showtime> remaining = showtimeRepository.findAll();
        assertThat(remaining).hasSize(1)
                .extracting(Showtime::getId)
                .containsExactly(showtime2.getId());
    }
}
