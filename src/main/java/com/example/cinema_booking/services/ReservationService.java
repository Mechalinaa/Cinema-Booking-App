package com.example.cinema_booking.services;

import com.example.cinema_booking.models.Cart;
import com.example.cinema_booking.models.Reservation;
import com.example.cinema_booking.models.ReservedSeat;
import com.example.cinema_booking.repositories.ReservationRepository;
import com.example.cinema_booking.repositories.ReservedSeatRepository;
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
    private final ReservedSeatRepository reservedSeatRepository;

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
                .map(seat -> {

                    boolean taken = reservedSeatRepository
                            .existsByShowtimeAndSeat(showtime, seat);

                    if (taken) {
                        throw new IllegalStateException(
                                "Miejsce już zajęte: rząd " + seat.getRowNum() +
                                        " miejsce " + seat.getSeatNum()
                        );
                    }

                    ReservedSeat rs = new ReservedSeat();
                    rs.setShowtime(showtime);
                    rs.setSeat(seat);
                    reservedSeatRepository.save(rs);

                    return "Rząd " + seat.getRowNum() + ", miejsce " + seat.getSeatNum();
                })
                .toList();

        reservation.setSeats(seats);

        return reservationRepository.save(reservation);
    }
}
