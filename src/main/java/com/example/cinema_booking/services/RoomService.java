package com.example.cinema_booking.services;

import com.example.cinema_booking.models.Room;
import com.example.cinema_booking.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    @Transactional
    public List<Room> findAll(){
        return roomRepository.findAll();
    }
}
