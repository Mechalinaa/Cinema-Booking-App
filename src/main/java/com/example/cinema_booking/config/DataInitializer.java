package com.example.cinema_booking.config;

import com.example.cinema_booking.model.Movie;
import com.example.cinema_booking.model.Room;
import com.example.cinema_booking.model.Seat;
import com.example.cinema_booking.model.Show;
import com.example.cinema_booking.service.MovieService;
import com.example.cinema_booking.service.RoomService;
import com.example.cinema_booking.service.SeatService;
import com.example.cinema_booking.service.ShowService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadData(MovieService movieService,
                               RoomService roomService,
                               SeatService seatService,
                               ShowService showService) {
        return args -> {

            if (!movieService.findAll().isEmpty()) return;

            // === 1. Filmy ===
            Movie m1 = movieService.save(Movie.builder().title("Inception").genre("Sci-Fi").durationInMinutes(148).build());
            Movie m2 = movieService.save(Movie.builder().title("Interstellar").genre("Sci-Fi").durationInMinutes(169).build());
            Movie m3 = movieService.save(Movie.builder().title("Gladiator").genre("Drama").durationInMinutes(155).build());

            // === 2. Sale ===
            Room r1 = roomService.save(Room.builder().name("Sala 1").rows(5).seatsPerRow(6).build());
            Room r2 = roomService.save(Room.builder().name("Sala 2").rows(4).seatsPerRow(5).build());

            // === 3. Miejsca w salach ===
            List<Seat> seatsR1 = new ArrayList<>();
            for (int row = 1; row <= r1.getRows(); row++) {
                for (int num = 1; num <= r1.getSeatsPerRow(); num++) {
                    Seat seat = seatService.save(Seat.builder().rowNum(row).seatNum(num).room(r1).build());
                    seatsR1.add(seat);
                }
            }
            r1.setSeats(seatsR1);

            List<Seat> seatsR2 = new ArrayList<>();
            for (int row = 1; row <= r2.getRows(); row++) {
                for (int num = 1; num <= r2.getSeatsPerRow(); num++) {
                    Seat seat = seatService.save(Seat.builder().rowNum(row).seatNum(num).room(r2).build());
                    seatsR2.add(seat);
                }
            }
            r2.setSeats(seatsR2);

            // === 4. Seanse ===
            showService.save(Show.builder().movie(m1).room(r1).startTime(LocalDateTime.now().plusDays(1)).build());
            showService.save(Show.builder().movie(m2).room(r1).startTime(LocalDateTime.now().plusDays(1).plusHours(3)).build());
            showService.save(Show.builder().movie(m3).room(r2).startTime(LocalDateTime.now().plusDays(2)).build());
        };
    }
}
