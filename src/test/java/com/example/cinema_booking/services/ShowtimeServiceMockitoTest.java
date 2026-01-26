package com.example.cinema_booking.services;

import com.example.cinema_booking.models.Movie;
import com.example.cinema_booking.models.Room;
import com.example.cinema_booking.models.Showtime;
import com.example.cinema_booking.repositories.ShowtimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShowtimeServiceMockitoTest {

    @Mock
    private ShowtimeRepository showRepository;

    @InjectMocks
    private ShowtimeService showtimeService;

    private Room room;
    private Movie movie;

    @BeforeEach
    void setUp() {
        room = new Room();
        room.setId(UUID.randomUUID());
        room.setName("Sala 1");

        movie = new Movie();
        movie.setId(UUID.randomUUID());
        movie.setTitle("Matrix");
        movie.setDurationInMinutes(120);
    }

    @Test
    void shouldSaveShowtimeSuccessfully() {
        Showtime newShow = new Showtime();
        newShow.setId(UUID.randomUUID());
        newShow.setRoom(room);
        newShow.setMovie(movie);
        newShow.setStartTime(LocalDateTime.of(2026,1,25,18,0));

        when(showRepository.findByRoom(room)).thenReturn(List.of());
        when(showRepository.save(newShow)).thenReturn(newShow);

        Showtime saved = showtimeService.save(newShow);

        assertEquals(newShow, saved);
        verify(showRepository).findByRoom(room);
        verify(showRepository).save(newShow);
    }

    @Test
    void shouldThrowExceptionIfRoomOrMovieIsNull() {
        Showtime showWithoutMovie = new Showtime();
        showWithoutMovie.setRoom(room);

        Showtime showWithoutRoom = new Showtime();
        showWithoutRoom.setMovie(movie);

        assertThrows(IllegalArgumentException.class, () -> showtimeService.save(showWithoutMovie));
        assertThrows(IllegalArgumentException.class, () -> showtimeService.save(showWithoutRoom));
    }

    @Test
    void shouldThrowExceptionIfShowtimeOverlaps() {
        Showtime existing = new Showtime();
        existing.setId(UUID.randomUUID());
        existing.setRoom(room);
        existing.setMovie(movie);
        existing.setStartTime(LocalDateTime.of(2026,1,25,18,0));

        Showtime newShow = new Showtime();
        newShow.setId(UUID.randomUUID());
        newShow.setRoom(room);
        newShow.setMovie(movie);
        newShow.setStartTime(LocalDateTime.of(2026,1,25,19,0)); // overlap: 18:00-20:00

        when(showRepository.findByRoom(room)).thenReturn(List.of(existing));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> showtimeService.save(newShow));
        assertTrue(ex.getMessage().contains("Kolizja seansów"));
        verify(showRepository).findByRoom(room);
        verify(showRepository, never()).save(newShow);
    }

    @Test
    void shouldReturnShowtimeById() {
        UUID id = UUID.randomUUID();
        Showtime show = new Showtime();
        show.setId(id);
        show.setRoom(room);
        show.setMovie(movie);

        when(showRepository.findByIdOrElseThrow(id)).thenReturn(show);

        Showtime result = showtimeService.findById(id);

        assertEquals(show, result);
        verify(showRepository).findByIdOrElseThrow(id);
    }

    @Test
    void shouldDeleteShowtimeById() {
        UUID id = UUID.randomUUID();

        doNothing().when(showRepository).deleteById(id);

        showtimeService.deleteById(id);

        verify(showRepository).deleteById(id);
    }
}
