package com.example.cinema_booking.services;

import com.example.cinema_booking.models.Seat;
import com.example.cinema_booking.repositories.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

    @Transactional(readOnly = true)
    public Seat findById(UUID id) {
        return seatRepository.findByIdOrElseThrow(id);
    }

    @Transactional(readOnly = true)
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Transactional
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }
}
