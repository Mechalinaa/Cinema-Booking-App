package com.example.cinema_booking.service;

import com.example.cinema_booking.model.Seat;
import com.example.cinema_booking.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public Seat save(Seat seat){
        return seatRepository.save(seat);
    }

    public List<Seat> findAll(){
        return seatRepository.findAll();
    }

    public Seat findById(Long id){
        return seatRepository.findById(id).orElse(null);
    }
}
