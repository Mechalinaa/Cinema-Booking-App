package com.example.cinema_booking.config;

import com.example.cinema_booking.model.*;
import com.example.cinema_booking.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadData(
            MovieService movieService,
            RoomService roomService,
            SeatService seatService,
            ShowService showService,
            ReservationService reservationService
    ) {
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

            // Sala 1
            List<Seat> seatsR1 = new ArrayList<>();
            for (int row = 1; row <= r1.getRows(); row++) {
                for (int num = 1; num <= r1.getSeatsPerRow(); num++) {
                    Seat seat = seatService.save(
                            Seat.builder()
                                    .rowNum(row)          // numer rzędu od 1
                                    .seatNum(num)         // numer miejsca od 1
                                    .reserved(false)      // domyślnie wolne
                                    .room(r1)
                                    .build()
                    );
                    seatsR1.add(seat);
                }
            }
            r1.setSeats(seatsR1);
            roomService.save(r1); // aktualizujemy relację dwustronną

            // Sala 2
            List<Seat> seatsR2 = new ArrayList<>();
            for (int row = 1; row <= r2.getRows(); row++) {
                for (int num = 1; num <= r2.getSeatsPerRow(); num++) {
                    Seat seat = seatService.save(
                            Seat.builder()
                                    .rowNum(row)
                                    .seatNum(num)
                                    .reserved(false)
                                    .room(r2)
                                    .build()
                    );
                    seatsR2.add(seat);
                }
            }
            r2.setSeats(seatsR2);
            roomService.save(r2);

            // === 4. Seanse ===
            Show s1 = showService.save(Show.builder().movie(m1).room(r1).startTime(LocalDateTime.now().plusDays(1).withHour(14).withMinute(0)).build());
            Show s2 = showService.save(Show.builder().movie(m2).room(r1).startTime(LocalDateTime.now().plusDays(1).withHour(17).withMinute(30)).build());
            Show s3 = showService.save(Show.builder().movie(m3).room(r2).startTime(LocalDateTime.now().plusDays(2).withHour(16).withMinute(0)).build());

            // === 5. Przykładowe rezerwacje ===
            Reservation rsv1 = new Reservation();
            rsv1.setTicketId("TICKET-001");
            rsv1.setShow(s1);
            rsv1.setSeats(List.of(seatsR1.get(0), seatsR1.get(1))); // pierwsze dwa miejsca
            reservationService.save(rsv1);

            Reservation rsv2 = new Reservation();
            rsv2.setTicketId("TICKET-002");
            rsv2.setShow(s2);
            rsv2.setSeats(List.of(seatsR1.get(2), seatsR1.get(3), seatsR1.get(4))); // kolejne trzy miejsca
            reservationService.save(rsv2);

            Reservation rsv3 = new Reservation();
            rsv3.setTicketId("TICKET-003");
            rsv3.setShow(s3);
            rsv3.setSeats(List.of(seatsR2.get(0))); // pierwsze miejsce w drugiej sali
            reservationService.save(rsv3);
        };
    }
}
