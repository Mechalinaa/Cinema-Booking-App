package com.example.cinema_booking.service;

import com.example.cinema_booking.model.Reservation;
import com.example.cinema_booking.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    public Reservation findById(Long id){
        return reservationRepository.findById(id).orElse(null);
    }
}
