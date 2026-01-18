package com.example.cinema_booking.services;

import com.example.cinema_booking.models.Reservation;
import com.example.cinema_booking.models.Showtime;
import com.example.cinema_booking.repositories.ReservationRepository;
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

    @Transactional(readOnly = true)
    public Reservation findById(UUID id){
        return reservationRepository.findByIdOrElseThrow(id);
    }

    @Transactional(readOnly = true)
    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    @Transactional
    public Reservation addNewReservation(UUID id, List<UUID> selectedSeats) {
        Showtime showtime = showtimeRepository.findByIdOrElseThrow(id);

        Reservation reservation = new Reservation();
        reservation.setMovieName(showtime.getMovie().getTitle());
        reservation.setStartTime(showtime.getStartTime());
        reservation.setSeats(reserveSeats(selectedSeats, showtime));

        return reservationRepository.save(reservation);
    }

    private static List<String> reserveSeats(List<UUID> selectedSeats, Showtime showtime) {
        return showtime.getRoom()
                .getSeats()
                .stream()
                .filter(seat -> selectedSeats.contains(seat.getId()))
                .peek(seat -> seat.setReserved(true))
                .map(seat -> seat.getSeatNum() + "-" + seat.getRowNum())
                .toList();
    }
}
