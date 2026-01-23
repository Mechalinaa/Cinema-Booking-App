package com.example.cinema_booking.services;

import com.example.cinema_booking.models.Cart;
import com.example.cinema_booking.models.Reservation;
import com.example.cinema_booking.repositories.ReservationRepository;
import com.example.cinema_booking.repositories.SeatRepository;
import com.example.cinema_booking.repositories.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ShowtimeRepository showtimeRepository;
    private final SeatRepository seatRepository;

    @Transactional(readOnly = true)
    public Reservation findById(UUID id){
        return reservationRepository.findByIdOrElseThrow(id);
    }

    @Transactional
    public Reservation createFromCart(Cart cart) {
        var showtime = showtimeRepository.findByIdOrElseThrow(cart.getShowtimeId());

        Reservation reservation = new Reservation();
        reservation.setMovieName(showtime.getMovie().getTitle());
        reservation.setStartTime(showtime.getStartTime());
        reservation.setTotalPrice(cart.getTotalPrice());

        List<String> seats = showtime.getRoom().getSeats()
                .stream()
                .filter(seat -> cart.getTickets().stream()
                        .anyMatch(t -> t.getSeatId().equals(seat.getId()))
                )
                .peek(seat -> {
                    seat.setReserved(true);
                    seatRepository.save(seat);
                })
                .map(seat -> "Rząd " + seat.getRowNum() + ", miejsce " + seat.getSeatNum())
                .toList();

        reservation.setSeats(seats);

        return reservationRepository.save(reservation);
    }
}
